package com.provider.citoCabs.base

import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Looper
import android.text.InputFilter
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.textfield.TextInputLayout
import com.provider.citoCabs.R
import de.hdodenhof.circleimageview.CircleImageView
import jp.wasabeef.glide.transformations.BlurTransformation
import org.joda.time.*
import java.io.InputStream
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


class Utils private constructor() {

    private var datePickerDialog: DatePickerDialog? = null

    private object HOLDER {
        val INSTANCE = Utils()
    }

    companion object {
        val init: Utils by lazy { HOLDER.INSTANCE }

        @JvmStatic
        @BindingAdapter("bind:imageUrl")
        fun setImage(image: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(image.context)
                    .apply {
                        this.setDefaultRequestOptions(postRequestOptions(url))
                    }
                    .load(url)
                    .thumbnail(0.5f)
//                    .centerCrop()
                    .into(image)
            }
        }

        @JvmStatic
        @BindingAdapter("bind:circleImageUrl")
        fun setCircleImage(image: CircleImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(image.context)
                    .apply {
                        this.setDefaultRequestOptions(postRequestOptions(url))
                    }
                    .load(url)
                    .thumbnail(0.5f)
//                    .centerCrop()
                    .into(image)
            }
        }

        fun postRequestOptions(imageUrl: String): RequestOptions {
            return RequestOptions().also {
                it.placeholder(R.drawable.ic_launcher_background)
                it.error(R.drawable.ic_launcher_background)
                it.diskCacheStrategy(DiskCacheStrategy.ALL)
                it.signature(ObjectKey(imageUrl))
                it.format(DecodeFormat.PREFER_RGB_565)
            }
        }

        @JvmStatic
        @BindingAdapter("bind:blurimageUrl")
        fun setBlurImage(image: ImageView, url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(image.context)
                    .apply {
                        this.setDefaultRequestOptions(postRequestOptions(url))
                    }.load(url)
                    .centerCrop()
                    .thumbnail(0.5f)
                    .apply(
                        RequestOptions.bitmapTransform(
                            BlurTransformation(
                                10, 3
                            )
                        )
                    )
                    .into(image)
            }
        }
    }


    fun centimeterToFeet(centemeter: String?): String? {
        try {
            var feetPart = 0
            var inchesPart = 0
            if (!TextUtils.isEmpty(centemeter)) {
                val dCentimeter = java.lang.Double.valueOf(centemeter.toString())
                feetPart = Math.floor(dCentimeter / 2.54 / 12).toInt()
                println(dCentimeter / 2.54 - feetPart * 12)
                inchesPart = Math.round(dCentimeter / 2.54 - feetPart * 12).toInt()
            }
            return String.format("%d' %d''", feetPart, inchesPart)
        } catch (e: java.lang.NumberFormatException) {
            return ""
        } catch (e: Exception) {
            return ""
        }

    }

    fun centimeterToFeetInches(centemeter: String?): String? {
        var feetPart = 0
        var inchesPart = 0
        if (!TextUtils.isEmpty(centemeter)) {
            val dCentimeter = java.lang.Double.valueOf(centemeter.toString())
            feetPart = Math.floor(dCentimeter / 2.54 / 12).toInt()
            println(dCentimeter / 2.54 - feetPart * 12)
            inchesPart = Math.round(dCentimeter / 2.54 - feetPart * 12).toInt()
        }
        return "${feetPart}.${inchesPart}"
    }


    fun convertFeetandInchesToCentimeter(feet: String?, inches: String?): Int {
        var heightInFeet = 0.0
        var heightInInches = 0.0
        try {
            if (feet != null && feet.trim { it <= ' ' }.length != 0) {
                heightInFeet = feet.toDouble()
            }
            if (inches != null && inches.trim { it <= ' ' }.length != 0) {
                heightInInches = inches.toDouble()
            }
        } catch (nfe: NumberFormatException) {
        }
        return Math.round(heightInFeet * 30.48 + heightInInches * 2.54).toInt()
    }

    fun checkIfHasNetwork(): Boolean {
        val connectivityManager =
            MainApplication.get()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    fun setFullWidth(inputLayout: TextInputLayout) {
        val errorTextView = inputLayout.findViewById<TextView>(R.id.textinput_error)
        var params = errorTextView?.layoutParams
        params?.width = 20
        params?.height = 20
        errorTextView?.layoutParams = params
    }

    fun getselectAge(day: Int, month: Int, year: Int, dobString: String): String {

        if (day == 0 || day > 31) {
            return "0 years"
        } else if (month == 0 || month > 12) {
            return "0 years"
        } else if (year == 0) {
            return "0 years"
        }

        Log.e("DOBBB", dobString)
        try {
            var date: Date? = null
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            try {

                date = sdf.parse(dobString)
            } catch (e: ParseException) {
                e.printStackTrace()
                return "0 years"
            }
            if (date == null) return "0 years"
            val dob = Calendar.getInstance()
            val today = Calendar.getInstance()



            Log.e("Month", date.month.toString())
            Log.e("Date", date.date.toString())
            dob.time = date


            val days = Days.daysBetween(LocalDate(date), LocalDate(today)).getDays()
            val months = Months.monthsBetween(LocalDate(date), LocalDate(today)).months
            val years = Years.yearsBetween(LocalDate(date), LocalDate(today)).years

            if (years > 0) {
                if (years == 1) {
                    return "1 year"
                } else {
                    return "${years} years"
                }
            } else if (months > 0) {
                if (months == 1) {
                    return "1 month"
                } else {
                    return "${months} months"
                }

            } else {
                if (days < 0) {
                    return "0 years"
                }
                if (days == 1) {
                    return "1 day"
                } else if (days == 0) {
                    return "0 day"
                } else {

                    return "${days} days"
                }

            }
        } catch (e: Exception) {
            return "0 years"
        }
    }

    fun checkDobAge(day: Int, month: Int, year: Int, dobString: String): Boolean {
        if (day == 0 || day > 31) {
            return false
        } else if (month == 0 || month > 12) {
            return false
        } else if (year == 0) {
            return false
        }
        try {
            var date: Date? = null
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            try {
                date = sdf.parse(dobString)
            } catch (e: ParseException) {
                return false
                e.printStackTrace()
            }
            if (date == null) return false
            val dob = Calendar.getInstance()
            val today = Calendar.getInstance()
            dob.time = date

            val days = Days.daysBetween(LocalDate(date), LocalDate(today)).getDays()
            val months = Months.monthsBetween(LocalDate(date), LocalDate(today)).months
            val years = Years.yearsBetween(LocalDate(date), LocalDate(today)).years

            if (years < 18 || years > 100) {
                return false
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun getAge(dobString: String): Int {
        try {
            var date: Date? = null
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val utcZone = TimeZone.getTimeZone("UTC")
            sdf.setTimeZone(utcZone)
            try {
                date = sdf.parse(dobString)
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            if (date == null) return 0
            val dob = Calendar.getInstance()
            val today = Calendar.getInstance()
            dob.time = date
            val year = dob[Calendar.YEAR]
            val month = dob[Calendar.MONTH]
            val day = dob[Calendar.DAY_OF_MONTH]
            return getUserAge(year, month, day)
        } catch (e: Exception) {
            return 0
        }
    }

    private fun getUserAge(year: Int, month: Int, day: Int): Int {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        dob[year, month] = day
        var age = today[Calendar.YEAR] - dob[Calendar.YEAR]
        if (today[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
            age--
        }
        val ageInt = age

        return if (ageInt == -1) return 0 else ageInt
    }

    fun setupFullScreen(activity: Activity) {
        activity.window.apply {
            decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
    }

    fun hideKeyBoard(context: Context, view: View) {
        val inputManager: InputMethodManager? =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputManager?.hideSoftInputFromWindow(view.applicationWindowToken, 0)
    }

    fun readJSONFromAsset(context: Context, path: String): String? {
        val json: String
        try {
            val inputStream: InputStream = context.assets.open(path)
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }


    fun getFilter(limit: Int, spaceEnable: Boolean): Array<InputFilter> {
        val inputFilter = InputFilter(fun(
            source: CharSequence,
            start: Int,
            end: Int,
            _: Spanned,
            _: Int,
            _: Int
        ): String? {
            for (index in start until end) {
                val type = Character.getType(source[index])
                if (spaceEnable) {
                    if (type == Character.SURROGATE.toInt() || type == Character.NON_SPACING_MARK.toInt()) {
                        return ""
                    }
                } else {
                    if (type == Character.SURROGATE.toInt() || type == Character.NON_SPACING_MARK.toInt() || type == Character.OTHER_SYMBOL.toInt() || Character.isWhitespace(
                            source[index]
                        )
                    ) {
                        return ""
                    }
                }
            }
            return null
        })
        return arrayOf(inputFilter, InputFilter.LengthFilter(limit))
    }

    fun selectDate(
        context: Context?,
        startFrom: String,
        tvDate: TextView,
        disableFutureDate: Boolean
    ) {
        datePickerDialog?.let {
            if (it.isShowing) {
                return
            }
        }
        val calendar = Calendar.getInstance()
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy")
        var date: Date? = null
        var startDate: Date? = null
        for (dateFormat in dateFormats) {
            try {
                startDate = SimpleDateFormat(dateFormat).parse(startFrom)
                date = SimpleDateFormat(dateFormat).parse(startFrom)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {
            calendar.time = date
        }
        val mYear = calendar[Calendar.YEAR]
        val mMonth = calendar[Calendar.MONTH]
        val mDay = calendar[Calendar.DAY_OF_MONTH]
        datePickerDialog = DatePickerDialog(
            context!!,
//            R.style.MyTimePickerDialogTheme,
            OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->

                Log.e("year", year.toString())
                Log.e("month", month.toString())
                Log.e("day", dayOfMonth.toString())

                var calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, (month));

                var month_date = SimpleDateFormat("MMMM");

                var day =
                    if (dayOfMonth.toString().length < 2) "0${dayOfMonth.toString()}" else dayOfMonth.toString()

                var monthh =
                    if (month + 1 < 2) "0${(month + 1).toString()}" else (month + 1).toString()

                var date = DateFormat.getDateTimeInstance().format(date);
                tvDate.setText("${year}-${monthh}-${day}")


            },
            mYear,
            mMonth,
            mDay
        )
        if (disableFutureDate) {
            val dob = Calendar.getInstance()
            datePickerDialog?.datePicker?.maxDate = dob.time.time
        } else {
            startDate?.let {
                datePickerDialog?.datePicker?.minDate = it.time
            }
        }
        datePickerDialog?.show()
    }

    fun selectDateWithTime(
        context: Context?,
        startFrom: String,
        editText: TextView,
        disableFutureDate: Boolean
    ) {
        datePickerDialog?.let {
            if (it.isShowing) {
                return
            }
        }
        val calendar = Calendar.getInstance()
        val dateFormats =
            listOf(
                "yyyy-MM-dd hh:mm:ss",
                "d MMMM yyyy hh:mm:ss",
                "yyyy/MM/dd hh:mm:ss",
                "dd/MM/yyyy hh:mm:ss",
                "MM/dd/yyyy hh:mm:ss"
            )
        var date: Date? = null
        var startDate: Date? = null
        for (dateFormat in dateFormats) {
            try {
                startDate = SimpleDateFormat(dateFormat).parse(getCurrentDate())
                date = SimpleDateFormat(dateFormat).parse(editText.text.toString())
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {
            calendar.time = date
        }


//        var currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date());

        calendar.add(Calendar.MINUTE, 30)
        val mYear = calendar[Calendar.YEAR]
        val mMonth = calendar[Calendar.MONTH]
        val mDay = calendar[Calendar.DAY_OF_MONTH]
        val startHour = calendar[Calendar.HOUR_OF_DAY]
        val startMinute = calendar[Calendar.MINUTE]
        datePickerDialog = DatePickerDialog(
            context!!,
            R.style.Theme_AppCompat_Light_Dialog,
            OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
                Log.e("CalendarTimeee", calendar.time.toString())
                TimePickerDialog(
                    context,
                    R.style.Theme_AppCompat_Light_Dialog,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                        val datetime = Calendar.getInstance();
                        val c = Calendar.getInstance();
                        c.set(Calendar.SECOND, 0)
                        datetime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        datetime.set(Calendar.MONTH, month);
                        datetime.set(Calendar.YEAR, year);
                        datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        datetime.set(Calendar.MINUTE, minute);
                        datetime.set(Calendar.SECOND, 0);

                        val formatter = SimpleDateFormat("dd/MM/yyyy hh:mm:ss");


                        var selectedDate = formatter.format(Date(datetime.timeInMillis))
                        var currentDate = formatter.format(Date(c.timeInMillis + ((1000 * 60) * 4)))

                        Log.e(
                            "SelectedTime  :",
                            formatter.format(Date(datetime.timeInMillis))
                        )
                        Log.e(
                            "CurrentTime :",
                            formatter.format(Date(c.timeInMillis + ((1000 * 60) * 4)))
                        )

                        if (datetime.timeInMillis > (c.timeInMillis + ((1000 * 60) * 30))) {
                            val pickedDateTime = Calendar.getInstance()
                            pickedDateTime.set(year, month, dayOfMonth, hourOfDay, minute)
                            Log.e("PickedDateTime", pickedDateTime.time.toString())
                            var currentTime = SimpleDateFormat(
                                "hh:mm a"
                            ).format(pickedDateTime.time);
                            editText.text =
                                getFormattedDateAddTask(year.toString() + "-" + (month + 1) + "-" + dayOfMonth) + ", " + currentTime
                        } else {
                            //it's before current'

                            val time = Calendar.getInstance()
                            time.add(Calendar.MINUTE, 30)
                            val date = SimpleDateFormat("yyyy-mm-dd").format(Date())
                            var endTime = SimpleDateFormat("hh:mm aa").format(time.time)
                            editText.setText("${date}, ${endTime}")
                            Toast.makeText(
                                context,
                                context.getString(R.string.time_validation),
                                Toast.LENGTH_SHORT
                            ).show();
                        }
                    },


//                    doSomethingWith(pickedDateTime)
                    startHour, startMinute, true
                ).show()


            },
            mYear,
            mMonth,
            mDay
        )

        Log.e("StartDateee0", startDate.toString())


        if (disableFutureDate) {
            val dob = Calendar.getInstance()
            dob.set(Calendar.YEAR, 2008)
            datePickerDialog?.datePicker?.maxDate = dob.time.time
        } else {
            startDate?.let {
                datePickerDialog?.datePicker?.minDate = it.time
            }
        }
        datePickerDialog?.show()
    }


    fun getFormattedDateAddTask(time: String): String {
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd")
        var currentTime = ""
        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(time)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {
            currentTime = SimpleDateFormat("d MMMM yyyy").format(date)
        }
        return currentTime
    }


    fun selectDateWithAge(
        context: Context?,
        startFrom: String,
        tvDate: TextView,
        totalAge: TextView,
        disableFutureDate: Boolean
    ) {
        datePickerDialog?.let {
            if (it.isShowing) {
                return
            }
        }
        val calendar = Calendar.getInstance()
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy")
        var date: Date? = null
        var startDate: Date? = null
        for (dateFormat in dateFormats) {
            try {
                startDate = SimpleDateFormat(dateFormat).parse(startFrom)
                date = SimpleDateFormat(dateFormat).parse(startFrom)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {
            calendar.time = date
        }
        val mYear = calendar[Calendar.YEAR]
        val mMonth = calendar[Calendar.MONTH]
        val mDay = calendar[Calendar.DAY_OF_MONTH]
        datePickerDialog = DatePickerDialog(
            context!!,
//            R.style.MyTimePickerDialogTheme,
            OnDateSetListener { _: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->

                Log.e("year", year.toString())
                Log.e("month", month.toString())
                Log.e("day", dayOfMonth.toString())

                var calendar = Calendar.getInstance();
                calendar.set(Calendar.MONTH, (month));

                var month_date = SimpleDateFormat("MMMM");

                var day =
                    if (dayOfMonth.toString().length < 2) "0${dayOfMonth.toString()}" else dayOfMonth.toString()

                var monthh =
                    if (month + 1 < 10) "0${(month + 1).toString()}" else (month + 1).toString()

                tvDate.setText("${day}/${monthh}/${year}")
                totalAge.setText(
                    "You are ${
                        getselectAge(
                            day.toInt(),
                            month.toInt(),
                            year.toInt(),
                            "${day}/${monthh}/${year}"
                        )
                    } old."
                )


            },
            mYear,
            mMonth,
            mDay
        )
        if (disableFutureDate) {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.YEAR, -18);
            var dob = calendar

            datePickerDialog?.datePicker?.maxDate = dob.time.time
        }
        val cal = Calendar.getInstance()
        cal.add(Calendar.YEAR, -100);

        datePickerDialog?.datePicker?.minDate = cal.time.time
        datePickerDialog?.show()
    }


    fun showTimePickerDialog(
        context: Context?,
        startFrom: String,
        editText: TextView,
        selectedDate: String
    ) {
        var mcurrentTime = Calendar.getInstance();
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy", "d MMMM, yyyy")


        try {
            var time = SimpleDateFormat("hh:mm aa").parse(startFrom)
            mcurrentTime.time = time

        } catch (e: java.lang.Exception) {
            e.printStackTrace()

        }


        var hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        var minute = mcurrentTime.get(Calendar.MINUTE);
        var mTimePicker = TimePickerDialog(
            context,
            object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                    var datetime = Calendar.getInstance();
                    var c = Calendar.getInstance();
                    datetime.set(Calendar.HOUR_OF_DAY, p1);
                    datetime.set(Calendar.MINUTE, p2);

                    if (selectedDate.isNotEmpty()) {

                        var date = SimpleDateFormat("d MMMM, yyyy").parse(selectedDate)

                        val monthNumber = SimpleDateFormat("MM").format(date)
                        val day = SimpleDateFormat("dd").format(date)

                        Log.e("monthNumberr", monthNumber)
                        Log.e("Dayaaa", day)
                        datetime.set(Calendar.MONTH, monthNumber.toInt() - 1);
                        datetime.set(Calendar.DAY_OF_MONTH, day.toInt());
                    }


                    var tTime = SimpleDateFormat("hh:mm aa").format(datetime.time);

                    if (datetime.timeInMillis < c.timeInMillis - 1000) {
                        Toast.makeText(
                            context,
                            "Please select the time which is equal or greater than the current time.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        editText.setText(tTime);
                    }

                }


            },
            hour,
            minute,
            false
        );//Yes 24 hour time
        mTimePicker.show();

    }


    fun getFormattedDate(time: String): String {
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy")

        var currentTime = ""
        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(time)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {
            currentTime = SimpleDateFormat("yyyy-MM-dd").format(date)
        }
        return currentTime
    }

    fun getServerFormattedDate(time: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        var currentTime = ""
        var date: Date? = null
        try {
            date = dateFormat.parse(time)
        } catch (e: java.lang.Exception) {
            Log.d("erverFormattedDate", "getFormattedDate: unable to parse date.")
        }

        if (date != null) {
            currentTime = SimpleDateFormat("d MMMM yyyy, hh:mm a").format(date)
        }
        return currentTime
    }

    fun getServerFormattedDateAgent(time: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd, hh:mm aa")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        var currentTime = ""
        var date: Date? = null
        try {
            date = dateFormat.parse(time)
        } catch (e: java.lang.Exception) {
            Log.d("DateAgentException", "getFormattedDate: unable to parse date.")
        }

        if (date != null) {
            currentTime = SimpleDateFormat("d MMMM yyyy, hh:mm a").format(date)
        }
        return currentTime
    }

    fun getServerFormattedDateCreateHangout(time: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        var currentTime = ""
        var date: Date? = null
        try {
            date = dateFormat.parse(time)
        } catch (e: java.lang.Exception) {
            Log.d("Exception", "getFormattedDate: unable to parse date.")
        }

        if (date != null) {
            currentTime = SimpleDateFormat("d MMMM, yyyy").format(date)
        }
        return currentTime
    }

    fun getServerFormattedDateHangoutTime(time: String): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        var currentTime = ""
        var date: Date? = null
        try {
            date = dateFormat.parse(time)
        } catch (e: java.lang.Exception) {
            Log.d("Exception", "getFormattedDate: unable to parse date.")
        }

        if (date != null) {
            currentTime = SimpleDateFormat("hh:mm aa").format(date)
        }
        return currentTime
    }


    fun getHangoutFormattedDate(time: String): String {
        var currentTime = ""
        var date: Date? = null
        try {
            date = SimpleDateFormat("dd MMM, yyyy").parse(time)
        } catch (e: java.lang.Exception) {
            Log.d("Exception", "getFormattedDate: unable to parse date.")
        }

        if (date != null) {
            currentTime = SimpleDateFormat("yyyy-MM-dd").format(date)
        }
        return currentTime!!
    }

    fun getCurrentDate(): String {
        val df = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = Calendar.getInstance().timeInMillis
        return df.format(calendar.time)
    }


    fun getDate(dateString: String): Date {
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy")
        val calendar = Calendar.getInstance()
        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(dateString)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        date?.let {
            calendar.time = date
        }
        return calendar.time
    }

    fun getTaskTypeDate(dateString: String): String {
        val dateFormats = listOf("yyyy-MM-dd", "yyyy/MM/dd", "dd/MM/yyyy")
        var currentTime = ""
        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(dateString)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        if (date != null) {

            val df = SimpleDateFormat("yyyy-MM-dd").format(date)
            Log.e("CurrenTimeee", df)

            var monthName = SimpleDateFormat("MMM").format(date)
            var dayName = SimpleDateFormat("EEEE").format(date)
            var day = SimpleDateFormat("dd").format(date)


            currentTime = "${dayName} ${monthName} ${day}"
        }
        return currentTime
    }

    fun getelapsedHours(oldDate: String?): Long {
        return try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val createdDate = DateTime(dateFormat.parse(oldDate)).toDate()
            println("createdDate : ${createdDate}  CurrentTime ${Calendar.getInstance().time}")
            TimeUnit.HOURS.convert(
                Calendar.getInstance().time.time - createdDate.time,
                TimeUnit.MILLISECONDS
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            0
        }
    }

    fun getElapsedMinutes(oldDate: String?): Long {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
//            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            val createdDate = DateTime(dateFormat.parse(oldDate)).toDate()
            val timeMinute = TimeUnit.MINUTES.convert(
                Calendar.getInstance().time.time - createdDate.time,
                TimeUnit.MILLISECONDS
            )
            println("Minute : $timeMinute createdDate : ${createdDate}  CurrentTime ${Calendar.getInstance().time}")

            return timeMinute
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return 0
        }
    }

    fun getElapsedDays(oldDate: String?): Long {
        val dateFormats = listOf("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(oldDate)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        try {
            val createdDate = DateTime(date).toDate()
            val timeMinute = TimeUnit.DAYS.convert(
                Calendar.getInstance().time.time - createdDate.time,
                TimeUnit.MILLISECONDS
            )
            println("Minute : $timeMinute createdDate : ${createdDate}  CurrentTime ${Calendar.getInstance().time}")

            return timeMinute
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return 0
        }
    }

    fun getSubscriptionEndDate(oldDate: String?): String {

        val dateFormats = listOf("yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")

        var date: Date? = null
        for (dateFormat in dateFormats) {
            try {
                date = SimpleDateFormat(dateFormat).parse(oldDate)
            } catch (e: java.lang.Exception) {
                Log.d("Exception", "getFormattedDate: unable to parse date.")
            }
        }
        try {
            val dtOrg = DateTime(date)
            val endDate = dtOrg.plusDays(30).toDate()

            val tTime = SimpleDateFormat("dd MMMM yyyy").format(endDate);

            return tTime
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return ""
        }
    }


}


operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
    val value = (this.value ?: ArrayList()).apply {
        addAll(values)
    }
    this.value = value
}

fun View.disableMultitap() {
    try {
        isEnabled = false
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            isEnabled = true
        }, 500)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}



