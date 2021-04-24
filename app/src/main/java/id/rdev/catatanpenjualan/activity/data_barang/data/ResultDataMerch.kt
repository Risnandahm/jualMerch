package id.rdev.catatanpenjualan.activity.data_merch.data

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName
import id.rdev.catatanpenjualan.model.Barang

@Generated("com.robohorse.robopojogenerator")
data class ResultDataMerch(

    @field:SerializedName("merch")
	val barang: List<Barang?>? = null,

    @field:SerializedName("message")
	val message: String? = null,

    @field:SerializedName("status")
	val status: Int? = null
)