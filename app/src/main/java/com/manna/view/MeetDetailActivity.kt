package com.manna.view

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.manna.Logger
import com.manna.R
import com.manna.di.ApiModule
import com.manna.ext.ViewUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.MultipartPathOverlay
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_meet_detail.*

enum class WayMode(val type: String) {
    WALKING("Walking"),
    TRANSIT("Transit")
}

data class WayPoint(
    val point: LatLng,
    val mode: String,
    val titles: List<String> = emptyList()
) {

    fun getPoint(): String = "${point.latitude},${point.longitude}"
}

@AndroidEntryPoint
class MeetDetailActivity : AppCompatActivity(), OnMapReadyCallback {

    private val viewModel: MeetDetailViewModel by viewModels()

    @SuppressLint("CheckResult")
    @UiThread
    override fun onMapReady(naverMap: NaverMap) {

        val endPoint = WayPoint(LatLng(37.492642, 127.026208), "End")

        ApiModule.provideBingApi()
            .getRoute(
                startLatLng = "37.482087,126.976742",
                endLatLng = endPoint.getPoint()
            )
            .subscribeOn(Schedulers.io())
            .map { root ->
                val items =
                    root.resourceSets?.first()?.resources?.first()?.routeLegs?.first()?.itineraryItems

                val points = mutableListOf<WayPoint>()

                items?.forEach {
                    val mode = it.details?.first()?.mode

                    it.maneuverPoint?.coordinates?.let { point ->
                        points.add(WayPoint(LatLng(point[0], point[1]), mode.orEmpty()))
                    }
                }

                points.add(endPoint)
                points
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->

                val sources = list.mapIndexedNotNull { index, wayPoint ->
                    when (wayPoint.mode) {
                        "Transit" -> {
                            ApiModule.provideBingApi()
                                .getRouteDriving(
                                    "${wayPoint.point.latitude},${wayPoint.point.longitude}",
                                    "${list.get(index + 1).point.latitude},${list.get(index + 1).point.longitude}"
                                )
                                .subscribeOn(Schedulers.io())
                                .map { root ->
                                    val items =
                                        root.resourceSets?.first()?.resources?.first()?.routeLegs?.first()?.itineraryItems

                                    val points = mutableListOf<WayPoint>()
//                                    points.add(wayPoint)

                                    items?.forEach {
                                        val mode = it.details?.first()?.mode

                                        it.maneuverPoint?.coordinates?.let { point ->
                                            points.add(
                                                WayPoint(
                                                    LatLng(point[0], point[1]),
                                                    mode.orEmpty()
                                                )
                                            )
                                        }
                                    }

                                    points
                                }
                                .observeOn(AndroidSchedulers.mainThread())
                        }
                        "Walking" -> {
                            ApiModule.provideBingApi()
                                .getRouteWalking(
                                    "${wayPoint.point.latitude},${wayPoint.point.longitude}",
                                    "${list.get(index + 1).point.latitude},${list.get(index + 1).point.longitude}"
                                )
                                .subscribeOn(Schedulers.io())
                                .map { root ->
                                    val items =
                                        root.resourceSets?.first()?.resources?.first()?.routeLegs?.first()?.itineraryItems

                                    val points = mutableListOf<WayPoint>()
//                                    points.add(wayPoint)

                                    items?.forEach {
                                        val mode = it.details?.first()?.mode

                                        it.maneuverPoint?.coordinates?.let { point ->
                                            points.add(
                                                WayPoint(
                                                    LatLng(point[0], point[1]),
                                                    mode.orEmpty()
                                                )
                                            )
                                        }
                                    }

                                    points
                                }
                                .observeOn(AndroidSchedulers.mainThread())
                        }
                        else -> {
                            null
                        }
                    }
                }

                Observable.zip(sources) {
                    it
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({array->
                        val list = array.flatMap {
                            it as List<WayPoint>
                        }.toMutableList()
                        list.add(endPoint)

                        list.forEach {
                            Logger.d("$it")
                        }

                        drawLine(naverMap, list.map { it.point })

                        val cameraUpdate = CameraUpdate.scrollTo(list.first().point)
                        naverMap.moveCamera(cameraUpdate)

                    }, {
                        Logger.d("$it")
                    })

            }, {
                Logger.d("$it")
            })
    }

    private fun drawLine(naverMap: NaverMap, points: List<LatLng>) {
        val multipartPath = MultipartPathOverlay()

        multipartPath.coordParts = listOf(
            points
//            listOf(
//                LatLng(37.5744287, 126.982625),
//                LatLng(37.57152, 126.97714),
//                LatLng(37.56607, 126.98268)
//            ),
//            listOf(
//                LatLng(37.56607, 126.98268),
//                LatLng(37.55845, 126.98207),
//                LatLng(37.55855, 126.97822)
//            ),
//            listOf(
//                LatLng(37.56607, 126.98268),
//                LatLng(37.56345, 126.97607),
//                LatLng(37.56755, 126.96722)
//            ),
//            listOf(
//                LatLng(37.56607, 126.98268),
//                LatLng(37.56445, 126.99707),
//                LatLng(37.55855, 126.99822)
//            )
        )

        multipartPath.colorParts = listOf(
            MultipartPathOverlay.ColorPart(
                Color.RED, Color.WHITE, Color.GRAY, Color.LTGRAY
            )
//            ,
//            MultipartPathOverlay.ColorPart(
//                Color.GREEN, Color.WHITE, Color.DKGRAY, Color.LTGRAY
//            ),
//            MultipartPathOverlay.ColorPart(
//                Color.BLUE, Color.WHITE, Color.DKGRAY, Color.LTGRAY
//            ),
//            MultipartPathOverlay.ColorPart(
//                Color.BLACK, Color.WHITE, Color.DKGRAY, Color.LTGRAY
//            )
        )

        multipartPath.map = naverMap

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meet_detail)

        viewModel.getPlaceDetail()

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as MapFragment?
            ?: MapFragment.newInstance().also {
                supportFragmentManager.beginTransaction().add(R.id.map, it).commit()
            }

        mapFragment.getMapAsync(this)

        ViewUtil.setStatusBarTransparent(this)

        top_panel.fitsSystemWindows = true

        btn_back.setOnClickListener {
            onBackPressed()
        }

        btn_menu.setOnClickListener {
            drawer.openDrawer(side_panel)
        }

        BottomSheetBehavior.from(bottom_sheet)
            .addBottomSheetCallback(createBottomSheetCallback(bottom_sheet_state))
    }

    private fun createBottomSheetCallback(text: TextView): BottomSheetCallback =
        object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {

                text.text = when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> "STATE DRAGGING"
                    BottomSheetBehavior.STATE_EXPANDED -> "STATE EXPANDED"
                    BottomSheetBehavior.STATE_COLLAPSED -> "STATE COLLAPSED"
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        String.format(
                            "STATE_HALF_EXPANDED\\nhalfExpandedRatio = %.2f",
                            BottomSheetBehavior.from(bottomSheet).halfExpandedRatio
                        )
                    }
                    else -> {
                        text.text.toString()
                    }
                }
            }

            override fun onSlide(
                bottomSheet: View,
                slideOffset: Float
            ) {
            }
        }
}
