package com.myapp.ui.feature

import androidx.compose.ui.unit.IntSize
import com.arkivanov.decompose.extensions.compose.jetbrains.rememberRootComponent
import com.myapp.App
import com.myapp.ui.navigation.NavHostComponent
import com.myapp.ui.value.MyAppTheme
import com.theapache64.cyclone.core.Activity
import com.theapache64.cyclone.core.Intent
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import androidx.compose.desktop.Window as setContent

/**
 * The activity who will be hosting all screens in this app
 */
class MainActivity : Activity() {
    companion object {
        fun getStartIntent(): Intent {
            return Intent(MainActivity::class).apply {
                // data goes here
            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        setContent(
            title = "${App.appArgs.appName} (${App.appArgs.version})",
            icon = getAppIcon(),
            size = IntSize(1024, 600),
        ) {
            MyAppTheme {
                // Igniting navigation
                rememberRootComponent(factory = ::NavHostComponent)
                    .render()
            }
        }

    }

    /**
     * To get app icon for toolbar and system tray
     */
    private fun getAppIcon(): BufferedImage {

        // Retrieving image
        val resourceFile = MainActivity::class.java.classLoader.getResourceAsStream(
            "drawables/launcher_icons/system.png" // We need a png
        )
        val imageInput = ImageIO.read(resourceFile)

        val newImage = BufferedImage(
            imageInput.width,
            imageInput.height,
            BufferedImage.TYPE_INT_ARGB
        )

        // Drawing
        val canvas = newImage.createGraphics()
        canvas.drawImage(imageInput, 0, 0, null)
        canvas.dispose()

        return newImage
    }
}