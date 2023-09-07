package com.slaytertv.psicofire.util

import android.R
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment

class Extensions {
}
//extensiones para mostrar y ocultar vistas como progressbar
fun View.hide(){ visibility = View.GONE }
fun View.show(){ visibility = View.VISIBLE }
//extension para habilitar y desabilitar
fun View.disable(){ isEnabled = true }
fun View.enable(){ isEnabled = false }
//clickable
fun View.clickdisable(){ isClickable = false }
fun View.clickenable(){ isClickable = true }
//color
fun View.colordisable(){
    setBackgroundColor(Color.RED)
    hide()
}
fun View.colorenable(){
    setBackgroundColor(Color.GREEN)
    show()
}

//extension para el Toast
fun Fragment.toast(msg:String?){ Toast.makeText(requireContext(),msg, Toast.LENGTH_LONG).show() }
fun Fragment.dialogx(msg:String?){
    val builder = AlertDialog.Builder(requireContext(),R.style.Theme_Dialog)
    builder.setTitle("Mensaje")
    builder.setMessage(msg)
    builder.show()
}

//chipgroup q almacena los tags
/*fun ChipGroup.addChip(
    text: String,
    isTouchTargeSize: Boolean = false,
    closeIconListener: View.OnClickListener? = null
) {
    //se llama al xml
    val chip: Chip = LayoutInflater.from(context).inflate(R.layout.item_chip,null,false) as Chip
    chip.text = if (text.length > 9) text.substring(0,9) + "..." else text
    chip.isClickable = false
    chip.setEnsureMinTouchTargetSize(isTouchTargeSize)
    if (closeIconListener != null){
        chip.closeIcon = ContextCompat.getDrawable(context, com.google.android.material.R.drawable.ic_mtrl_chip_close_circle)
        chip.isCloseIconVisible = true
        chip.setOnCloseIconClickListener(closeIconListener)
    }
    addView(chip)
}*/
//funcion para crear dialog
fun Context.createDialog(layout: Int, cancelable: Boolean): Dialog {
    val dialog = Dialog(this, R.style.Theme_Dialog)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(layout)
    dialog.window?.setGravity(Gravity.CENTER)
    dialog.window?.setLayout(
        WindowManager.LayoutParams.MATCH_PARENT,
        WindowManager.LayoutParams.WRAP_CONTENT
    )
    dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(cancelable)
    return dialog
}

val Int.dpToPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Int.pxToDp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()
//verifica q el campo email no este vacio
fun String.isValidEmail() =
    isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()