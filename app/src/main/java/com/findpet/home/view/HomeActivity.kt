package com.findpet.home.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.findpet.R
import com.findpet.animallist.view.AnimalListActivity
import com.findpet.animallist.view.AnimalViewModel
import com.findpet.animalregister.view.AnimalDetailsActivity
import com.findpet.animalregister.view.AnimalSelectionActivity
import com.findpet.extensions.showToast
import com.findpet.home.model.HomeItem
import com.findpet.home.type.HomeOptionType
import com.findpet.home.view.adapter.HomeRecyclerAdapter
import com.findpet.login.LoginActivity
import com.findpet.login.UserViewModel
import com.findpet.qrcode.QRCodeReaderActivity
import com.google.zxing.integration.android.IntentIntegrator
import data.Status
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.home_content_layout.view.*
import kotlinx.android.synthetic.main.home_header_layout.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModel()

    private val animalViewModel: AnimalViewModel by viewModel()

    private val homeAdapter: HomeRecyclerAdapter by lazy {
        HomeRecyclerAdapter(list) {
            when (it.homeOptionType) {
                HomeOptionType.REGISTER_ANIMAL -> redirectToRegisterAnimal()
                HomeOptionType.HANDLING_ANIMALS -> redirectToAnimalList()
                HomeOptionType.READ_QR_CODE -> redirectToScanQRCodeActivity()
            }
        }
    }

    private val list: MutableList<HomeItem> by lazy {
        mutableListOf<HomeItem>().apply {
            add(
                HomeItem(
                    R.drawable.icon_register_pet,
                    "Gerenciar PETs",
                    HomeOptionType.HANDLING_ANIMALS
                )
            )
            add(HomeItem(R.drawable.icon_qr_code, "Ler QR Code", HomeOptionType.READ_QR_CODE))
            add(
                HomeItem(
                    R.drawable.icon_handling_pets,
                    "Cadastrar novo PET",
                    HomeOptionType.REGISTER_ANIMAL
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        content.home_content_list.apply {
            adapter = homeAdapter
        }

        welcome_text.text =
            getString(R.string.welcome_text_formatter, userViewModel.getUserFistName())

        logout.setOnClickListener {
            userViewModel.clearUserSession()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        with(animalViewModel) {
            observeAnimal(this)
        }

    }

    private fun redirectToRegisterAnimal() {
        startActivity(Intent(this, AnimalSelectionActivity::class.java))
    }

    private fun redirectToAnimalList() {
        startActivity(AnimalListActivity.getStartIntent(this))
    }

    private fun redirectToScanQRCodeActivity() {
        IntentIntegrator(this)
            .setCaptureActivity(QRCodeReaderActivity::class.java)
            .setOrientationLocked(false)
            .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES)
            .setPrompt(getString(R.string.scanning_qr_code))
            .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        IntentIntegrator.parseActivityResult(requestCode, resultCode, data)?.contents?.let {
            animalViewModel.getAnimal(it)
        }
        super.onActivityResult(requestCode, resultCode, data)

    }

    private fun observeAnimal(animalViewModel: AnimalViewModel) {
        animalViewModel.getAnimalLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    loader.visibility = View.GONE
                    it.data?.let { animal ->
                        AnimalDetailsActivity.getStartIntent(this, animal)
                    }
                }
                Status.ERROR -> {
                    loader.visibility = View.GONE
                    showToast(R.string.generic_error)
                }

                Status.LOADING -> {
                    loader.visibility = View.VISIBLE
                }
            }
        })
    }
}