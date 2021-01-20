package jp.gr.java_conf.ya.sensorlist

import android.annotation.SuppressLint
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {
    private val sensorTypeList = intArrayOf(
            Sensor.TYPE_ACCELEROMETER,
            // Sensor.TYPE_ACCELEROMETER_UNCALIBRATED,
            Sensor.TYPE_AMBIENT_TEMPERATURE,
            // Sensor.TYPE_DEVICE_PRIVATE_BASE,
            Sensor.TYPE_GAME_ROTATION_VECTOR,
            Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_GYROSCOPE_UNCALIBRATED,
            // Sensor.TYPE_HEART_BEAT,
            Sensor.TYPE_HEART_RATE,
            Sensor.TYPE_LIGHT,
            Sensor.TYPE_LINEAR_ACCELERATION,
            // Sensor.TYPE_LOW_LATENCY_OFFBODY_DETECT,
            Sensor.TYPE_MAGNETIC_FIELD,
            Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED,
            // Sensor.TYPE_MOTION_DETECT,
            // Sensor.TYPE_POSE_6DOF,
            Sensor.TYPE_PRESSURE,
            Sensor.TYPE_PROXIMITY,
            Sensor.TYPE_RELATIVE_HUMIDITY,
            Sensor.TYPE_ROTATION_VECTOR,
            Sensor.TYPE_SIGNIFICANT_MOTION,
            // Sensor.TYPE_STATIONARY_DETECT,
            Sensor.TYPE_STEP_COUNTER,
            Sensor.TYPE_STEP_DETECTOR
    )

    private val sensorTypeNameList = arrayOf(
            "ACCELEROMETER",
            // "ACCELEROMETER_UNCALIBRATED",
            "AMBIENT_TEMPERATURE",
            // "DEVICE_PRIVATE_BASE",
            "GAME_ROTATION_VECTOR",
            "GEOMAGNETIC_ROTATION_VECTOR",
            "GRAVITY",
            "GYROSCOPE",
            "GYROSCOPE_UNCALIBRATED",
            // "HEART_BEAT",
            "HEART_RATE",
            "LIGHT",
            "LINEAR_ACCELERATION",
            // "LOW_LATENCY_OFFBODY_DETECT",
            "MAGNETIC_FIELD",
            "MAGNETIC_FIELD_UNCALIBRATED",
            // "MOTION_DETECT",
            // "POSE_6DOF",
            "PRESSURE",
            "PROXIMITY",
            "RELATIVE_HUMIDITY",
            "ROTATION_VECTOR",
            "SIGNIFICANT_MOTION",
            // "STATIONARY_DETECT",
            "STEP_COUNTER",
            "STEP_DETECTOR")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        val sensorManager: SensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // for (st in sensorTypeList) {
        // val sensors = sensorManager.getSensorList(st)
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        if (sensors.size > 0) {
            for (i in 0 until sensors.size) {
                sensorManager.registerListener(this, sensors[i], SensorManager.SENSOR_DELAY_NORMAL)
            }
        }
        // }
    }

    override fun onPause() {
        super.onPause()

        val sensorManager: SensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(event: SensorEvent?) {
        if (event == null) return

        val textView = findViewById<TextView>(R.id.text_view)
        val buf = StringBuilder().also {
            val es = event.sensor
            it.append(es.name)
            it.append("\t")
            it.append(es.vendor)
            it.append("\t")
            it.append(es.type)
            it.append("\n")
        }
        Log.v("BUF", buf.toString())
        textView.text = buf.toString() + textView.text.toString()
    }
}