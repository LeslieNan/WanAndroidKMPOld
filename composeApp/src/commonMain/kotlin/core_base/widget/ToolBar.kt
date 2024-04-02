//package com.leslienan.core_base.widget
//
//import androidx.activity.ComponentActivity
//import androidx.annotation.DrawableRes
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalAccessibilityManager
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.sp
//import com.leslienan.core_base.ui.theme.toolbarHeight
//
//@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
//@Composable
//fun Toolbar(
//    title: String = "",
//    @DrawableRes rightIcon: Int? = null,
//    rightText: String = "",
//    onRightClick: (() -> Unit)? = null,
//    @DrawableRes backIcon: Int? = null,
//    onBackClick: (() -> Unit)? = null,
//) {
//    Box(
//        Modifier
//            .fillMaxWidth()
//            .height(toolbarHeight)
//    ) {
//        val activity = (LocalContext.current as? ComponentActivity)
//        IconButton({
//            onBackClick?.invoke() ?: activity?.onBackPressedDispatcher?.onBackPressed()
//        }) {
//            backIcon?.let {
//                Icon(painterResource(it), null)
//            } ?: kotlin.run {
//                Icon(Icons.Filled.ArrowBack, null)
//            }
//        }
//        Text(
//            "标题", Modifier.align(Alignment.Center), Color.Black, 18.sp,
//            textAlign = TextAlign.Center
//        )
//        if (rightText.isNotEmpty()) {
//            Text(rightText, Modifier.clickable {
//                onRightClick?.invoke()
//            })
//        }
//        rightIcon?.let {
//            IconButton({
//                onRightClick?.invoke()
//            }, Modifier.align(Alignment.CenterEnd)) {
//                Icon(painter = painterResource(it), null)
//            }
//        }
//    }
//}