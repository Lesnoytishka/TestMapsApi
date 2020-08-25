package com.lesnoytishka.testmapsapi;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;

public class MainActivity extends AppCompatActivity {
//    https://habr.com/ru/post/480284/ - Очень нужныя штука!!! Но для экономии времени можно сразу лезьт ниже
//    https://github.com/yandex/mapkit-android-demo/tree/master/src/main/java/com/yandex/mapkitdemo
//
//    MasstransitRoutingActivity.java   - построение маршрута
//    MapObjectsActivity.java           - добавить пин на карту

    /**
     * Замените "your_api_key" валидным API-ключом.
     * Ключ можно получить на сайте https://developer.tech.yandex.ru/
     */
    private final String MAPKIT_API_KEY = "нужно внести ключ";

    private final Point TIMIRIAZEVSKIY_PARK = new Point(55.819530, 37.547078);
    private final Point AKUAPARK_KARIBIA = new Point(55.747841, 37.778475);


    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * Задайте API-ключ перед инициализацией MapKitFactory.
         * Рекомендуется устанавливать ключ в методе Application.onCreate,
         * но в данном примере он устанавливается в activity.
         */
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        /**
         * Инициализация библиотеки для загрузки необходимых нативных библиотек.
         * Рекомендуется инициализировать библиотеку MapKit в методе Activity.onCreate
         * Инициализация в методе Application.onCreate может привести к лишним вызовам и увеличенному использованию батареи.
         */
        MapKitFactory.initialize(this);
        // Создание MapView.
        setContentView(R.layout.second_activity);
        super.onCreate(savedInstanceState);
        mapView = (MapView)findViewById(R.id.mapview);

        mapView.getMap().move(
                new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);


        mapView.getMap().move(
                new CameraPosition(AKUAPARK_KARIBIA, 13.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 10),
                null);
        mapView.getMap().move(
                new CameraPosition(TIMIRIAZEVSKIY_PARK, 10.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 10),
                null);
        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawCircle(20,20,5, paint);
        mapView.draw(canvas);
    }

    @Override
    protected void onStop() {
        // Вызов onStop нужно передавать инстансам MapView и MapKit.
        mapView.onStop();
        MapKitFactory.getInstance().onStop();
        super.onStop();
    }

    @Override
    protected void onStart() {
        // Вызов onStart нужно передавать инстансам MapView и MapKit.
        super.onStart();
        MapKitFactory.getInstance().onStart();
        mapView.onStart();
    }
}