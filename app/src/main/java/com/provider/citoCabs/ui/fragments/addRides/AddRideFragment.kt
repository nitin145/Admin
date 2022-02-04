package com.provider.citoCabs.ui.fragments.addRides

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.paulrybitskyi.persistentsearchview.adapters.model.SuggestionItem
import com.paulrybitskyi.persistentsearchview.listeners.OnSuggestionChangeListener
import com.paulrybitskyi.persistentsearchview.utils.SuggestionCreationUtil
import com.provider.citoCabs.R
import com.provider.citoCabs.base.ScopedFragment
import com.provider.citoCabs.base.Utils
import com.provider.citoCabs.data.responeClasses.ResponseStatesItem
import com.provider.citoCabs.databinding.AddRideFragmentBinding
import com.provider.citoCabs.ui.fragments.addRides.viewModel.RidesViewModel
import com.provider.citoCabs.ui.fragments.addRides.viewModel.RidesViewModelFactory
import org.json.JSONException
import org.json.JSONObject
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AddRideFragment : ScopedFragment(), View.OnTouchListener, KodeinAware {
    override val kodein by lazy { (activity?.applicationContext as KodeinAware).kodein }
    private val viewModelFactory: RidesViewModelFactory by instance()
    lateinit var mViewModel: RidesViewModel
    lateinit var mBinding: AddRideFragmentBinding
    var listCity = ArrayList<String>()
    var carsList = ArrayList<String>()
    var packagesList = ArrayList<String>()
    var statesList = ArrayList<ResponseStatesItem>()
    var onSearch:String=""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::mBinding.isInitialized) {
            mViewModel =
                ViewModelProvider(this, viewModelFactory).get(RidesViewModel::class.java)
            mBinding = AddRideFragmentBinding.inflate(inflater, container, false).apply {
                clickHandler = ClickHandler()
            }
        }

        getList()
        setUpAdapters()
        setupCurrentTime()
        onTypeChange()
        setupObserver()
        OnSearchQueryChangeListener()


        mBinding.etCarType.setOnTouchListener(this)
        mBinding.etPickupCity.setOnTouchListener(this)
        mBinding.etDestinationCity.setOnTouchListener(this)
        mBinding.etDate.setOnTouchListener(this)
        mBinding.etState.setOnTouchListener(this)

        return mBinding.root
    }


    private fun setupObserver() {

        mViewModel.apply {
            addRideResponse.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
                if (it != null) {
                    showToast("Ride Added Successfully")
                    setFragmentResult(getString(R.string.add_ride), Bundle())
                    findNavController().navigateUp()
                }
            })

            showLoading.observe(viewLifecycleOwner, {
                if (it == true) {
                    showProgress()
                } else hideProgress()
            })

            showMessage.observe(viewLifecycleOwner, Observer {
                hideProgress()
                if (!it.isNullOrEmpty()) {
                    showToast(it)
                }

            })

        }

    }

    fun onTypeChange() {
        mBinding.rgType.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbLocal -> {
                    mBinding.tvDestinationCity.visibility = GONE
                    mBinding.etDestinationCity.visibility = GONE
                    mBinding.tvPickUpCity.text = getString(R.string.select_city)
                    mBinding.etPickupCity.setHint(getString(R.string.select_city))

                    mBinding.tvCar.text = getString(R.string.select_package)
                    mBinding.etCarType.setHint(getString(R.string.select_package))

                    mBinding.etCarType.setText("")

                    mBinding.etCarType.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.simple_spinner_dropdown_item,
                            R.id.tvName,
                            packagesList
                        )
                    )
                }
                R.id.rbOneWay, R.id.rbRound -> {
                    mBinding.tvDestinationCity.visibility = VISIBLE
                    mBinding.etDestinationCity.visibility = VISIBLE
                    mBinding.tvPickUpCity.text = getString(R.string.pickup_city)
                    mBinding.etPickupCity.setHint(getString(R.string.pickup_city))

                    mBinding.tvCar.text = getString(R.string.car_type)
                    mBinding.etCarType.setHint(getString(R.string.car_type))

                    mBinding.etCarType.setText("")

                    mBinding.etCarType.setAdapter(
                        ArrayAdapter(
                            requireContext(),
                            R.layout.simple_spinner_dropdown_item,
                            R.id.tvName,
                            carsList
                        )
                    )
                }

            }
        }
    }

    fun setupCurrentTime() {

        val time = Calendar.getInstance()
        time.add(Calendar.MINUTE, 30)
        val date = SimpleDateFormat("d MMMM yyyy").format(Date())
        var endTime = SimpleDateFormat("hh:mm aa").format(time.time)
        mBinding.etDate.setText("${date}, ${endTime}")
    }

    fun setUpAdapters() {

        //Add Cars List
        carsList.add("Hatchback")
        carsList.add("Sedan")
        carsList.add("Sedan With Carrier")
        carsList.add("Ertiga")
        carsList.add("Ertiga With Carrier")
        carsList.add("Innova")
        carsList.add("Innova With Carrier")
        carsList.add("Innova Crysta")
        carsList.add("Innova Crysta With Carrier")

        //Add Packages
        packagesList.add("8 Hours 80 KM")
        packagesList.add("12 hours 120 KM")
        packagesList.add("24 hours 240 KM")

        //Add State

        var states = ArrayList<String>()
        states.add("Punjab")
        states.add("Delhi")
        states.add("Chandigarh")
        states.add("Himachal Pradesh")
        states.add("Uttarakhand")
        states.add("Uttar Pradesh")
        states.add("Maharashtra")
        states.add("Haryana")
        states.add("Bihar")
        states.add("Madhya Pradesh")
        states.add("Jammu and Kashmir")
        states.add("Rajasthan")
        states.add("Gujarat")



        mBinding.rbOneWay.isChecked = true

        mBinding.etState.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                R.id.tvName,
                states
            )
        )
        mBinding.etPickupCity.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                R.id.tvName,
                listCity.distinct()
            )
        )

        mBinding.etDestinationCity.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                R.id.tvName,
                listCity.distinct()

            )
        )

        mBinding.persistentSearchView.apply {
            setSuggestions(SuggestionCreationUtil.asRecentSearchSuggestions(listCity), false)
            hideRightButton()
            hideLeftButton()
            isVoiceInputButtonEnabled=false
            setDismissOnTouchOutside(false)

        }


        mBinding.etCarType.setAdapter(
            ArrayAdapter(
                requireContext(),
                R.layout.simple_spinner_dropdown_item,
                R.id.tvName,
                carsList
            )
        )
    }

    fun OnSearchQueryChangeListener(){
        with(mBinding.persistentSearchView) {

            setOnLeftBtnClickListener {
            }
            setOnRightBtnClickListener {
                mBinding.persistentSearchView.visibility= GONE
            }
            setOnClearInputBtnClickListener {
            }

            setOnSearchConfirmedListener { searchView, query ->
            }


            setOnSearchQueryChangeListener { searchView, oldQuery, newQuery ->
                val filterList= listCity.filter { month -> month.startsWith(newQuery,true)}
                    println("NewQuery ${newQuery} OriginalSize${listCity.size} filterSize ${filterList}")
                setSuggestions(
                    if(newQuery.isBlank()) {
                        SuggestionCreationUtil.asRecentSearchSuggestions(listCity)
                    } else {
                        SuggestionCreationUtil.asRecentSearchSuggestions( filterList )
                    },
                    true
                )


            }

            setOnSuggestionChangeListener(object : OnSuggestionChangeListener {

                override fun onSuggestionPicked(suggestion: SuggestionItem) {
                    when(onSearch){
                        getString(R.string.pickup_city)->{
                            mBinding.etPickupCity.setText(suggestion.itemModel.text.toString())
                        }
                        getString(R.string.destination_city)->{
                            mBinding.etDestinationCity.setText(suggestion.itemModel.text.toString())
                        }
                    }
                    mBinding.persistentSearchView.visibility= GONE

                }

                override fun onSuggestionRemoved(suggestion: SuggestionItem) {
                    // Handle a suggestion remove event. This is the place where
                    // you'd want to remove the suggestion from your data provider.
                }

            })
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        event?.let { motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                v?.let { view ->
                    Utils.init.hideKeyBoard(requireContext(), mBinding.root)

                    when (view.id) {
                        R.id.etCarType, R.id.etState -> (view as AutoCompleteTextView).showDropDown()
                        R.id.etPickupCity->{
                            onSearch=getString(R.string.pickup_city)
                            mBinding.persistentSearchView.inputQuery=mBinding.etPickupCity.text.toString()
                            mBinding.persistentSearchView.visibility= VISIBLE
                            mBinding.persistentSearchView.expand()
                        }
                        R.id.etDestinationCity->{
                            onSearch=getString(R.string.destination_city)
                            mBinding.persistentSearchView.inputQuery=mBinding.etDestinationCity.text.toString()
                            mBinding.persistentSearchView.visibility= VISIBLE
                            mBinding.persistentSearchView.expand()
                        }
                        R.id.etDate -> {
                            Utils.init.selectDateWithTime(
                                requireContext(),
                                (view as TextView).text.toString(),
                                view,
                                false
                            )

                        }
                    }
                }
            }
        }
        return false
    }

    inner class ClickHandler {
        fun onBackPress() {
            findNavController().navigateUp()
        }


        fun addRide() {
            if (mBinding.etState.text.isEmpty()) {
                showToast("Please select Pickup State")
            } else if (mBinding.etPickupCity.text.isEmpty()) {
                showToast("Please enter Pickup City")
            } else if (mBinding.etDestinationCity.text.isEmpty() && !mBinding.rbLocal.isChecked) {
                showToast("Please enter Destination City")
            } else if (mBinding.etPickupCity.text.isEmpty()) {
                showToast("Please enter Price")
            } else if (mBinding.etCarType.text.isEmpty() && !mBinding.rbLocal.isChecked) {
                showToast("Please select Car Type")
            } else if (mBinding.etCarType.text.isEmpty() && mBinding.rbLocal.isChecked) {
                showToast("Please select package")
            } else if (mBinding.etMobile.text.isEmpty()) {
                showToast("Please enter contact number")
            } else {
                mViewModel.addRideParams.apply {
                    for (item in statesList) {
                        if (mBinding.etState.text.toString().equals(item.name, true)) {
                            stateId = item.id.toString()
                            break
                        }
                    }
                    dateTime = mBinding.etDate.text.toString()
                    rideType =
                        if (mBinding.rbRound.isChecked) "Round" else if (mBinding.rbLocal.isChecked) "Local" else "One Way"
                    pickUpDestination = mBinding.etPickupCity.text.toString()
                    price = mBinding.etPrice.text.toString()
                    travelerName = mBinding.etCompany.text.toString()
                    additionNotes = mBinding.etAdditionalNotes.text.toString()
                    contactNo = mBinding.etMobile.text.toString()
                    priceWithStateTax = mBinding.cbStateTax.isChecked
                    priceWithToll = mBinding.cbGSt.isChecked
                    if (mBinding.rbRound.isChecked || mBinding.rbOneWay.isChecked) {
                        cabType = mBinding.etCarType.text.toString()
                        endDestination = mBinding.etDestinationCity.text.toString()

                    } else {
                        packageType = mBinding.etCarType.text.toString()
                    }
                }
                mViewModel.addRide()

            }
        }


    }


    override fun stopBlur() {

    }


    override fun onDestroy() {
        super.onDestroy()
        hideProgress()
    }

    fun getList() {
        try {
            val jsonObject = JSONObject(
                requireContext().assets.open("states.json").bufferedReader().use { it.readText() })
            // Get Json array
            val array = jsonObject.getJSONArray("array")
            for (i in 0 until array.length()) {
                // select the particular JSON data
                val obj = array.getJSONObject(i)
                val city = obj.getString("name")
                val state = obj.getString("state")
                // add to the lists in the specified format
                listCity.add(city)
                listCity.add(state)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


}