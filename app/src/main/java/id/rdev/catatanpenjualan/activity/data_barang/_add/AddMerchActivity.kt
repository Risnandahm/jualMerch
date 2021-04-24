package id.rdev.catatanpenjualan.activity.data_merch._add

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import id.rdev.catatanpenjualan.R
import id.rdev.catatanpenjualan.activity.data_merch._add.presenter.AddMerchPresenter
import id.rdev.catatanpenjualan.activity.data_merch._add.presenter.AddMerchView
import id.rdev.catatanpenjualan.base.BaseActivity
import id.rdev.catatanpenjualan.model.Merch
import kotlinx.android.synthetic.main.activity_add_merch.*
import org.jetbrains.anko.toast
import java.io.Serializable

class AddMerchActivity : BaseActivity(), AddMerchView {

    override fun onCreate(savedInstanceState: Bundle?) {
        cekSesi(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_merch)

        val intent = intent.getSerializableExtra(TAGS.MERCH


        )

        if (intent != null) {
            setActionEditButton(intent)
        } else {
            setActionTambahButton()
        }
    }

    // edit Merch
    private fun setActionEditButton(serializable: Serializable) {
        btAddMerch.text = "Simpan"
        val merch = serializable as Merch
        etAddMerchBarcode.setText(merch.barcode)
        etAddMerchNamaMerch.setText(merch.namaMerch)
        etAddMerchKategori.setText(merch.kategori)
        etAddMerchHargaBeli.setText(merch.hargaBeli.toString())
        etAddMerchHargaJual.setText(merch.hargaJual.toString())

        btAddMerch.setOnClickListener {
            val barcode = etAddMerchBarcode.text.toString()
            val nama_merch = etAddMerchNamaMerch.text.toString()
            val kategori = etAddMerchKategori.text.toString()
            val harga_beli_s = etAddMerchHargaBeli.text.toString()
            val harga_jual_s = etAddMerchHargaJual.text.toString()

            if (harga_beli_s.isNotBlank() && harga_jual_s.isNotBlank()) {
                val harga_beli = harga_beli_s.toDouble()
                val harga_jual = harga_jual_s.toDouble()

                // buat objek Merch
                merch.idUser = user?.idUser.toString()
                merch.barcode = barcode
                merch.namaMerch = nama_merch.toUpperCase()
                merch.kategori = kategori.toLowerCase().capitalize()
                merch.hargaBeli = harga_beli
                merch.hargaJual = harga_jual

                // simpan ke database
                AddMerchPresenter(this@AddMerchActivity).updateMerch(merch)
            } else {
                // jangan input
                Snackbar.make(it, "Harga tidak boleh kosong", Snackbar.LENGTH_SHORT)
            }
        }
    }
    // tambah merch
    private fun setActionTambahButton() {
        btAddMerch.setOnClickListener {
            btAddMerch.text = "Tambah"
            val barcode = etAddMerchBarcode.text.toString()
            val nama_merch = etAddMerchNamaMerch.text.toString()
            val kategori = etAddMerchKategori.text.toString()
            val harga_beli_s = etAddMerchHargaBeli.text.toString()
            val harga_jual_s = etAddMerchHargaJual.text.toString()

            if (harga_beli_s.isNotBlank() && harga_jual_s.isNotBlank()) {
                val harga_beli = harga_beli_s.toDouble()
                val harga_jual = harga_jual_s.toDouble()

                // buat objek merch
                val merch = Merch()
                merch.idUser = user?.idUser.toString()
                merch.barcode = barcode
                merch.namaMerch = nama_merch.toUpperCase()
                merch.kategori = kategori.toLowerCase().capitalize()
                merch.hargaBeli = harga_beli
                merch.hargaJual = harga_jual

                // simpan ke database
                AddMerchPresenter(this@AddMerchActivity).addMerch(merch)
            } else {
                // jangan input
                Snackbar.make(it, "Harga tidak boleh kosong", Snackbar.LENGTH_SHORT)
            }
        }
    }

    /**
     * Result baik edit ataupun hapus sama saja
     */

    override fun onSuccessAddMerch(msg: String?) {
        toast(msg ?: "").show()
        finish()
    }

    override fun onErrorAddMerch(msg: String?) {
        toast(msg ?: "").show()
    }

}
