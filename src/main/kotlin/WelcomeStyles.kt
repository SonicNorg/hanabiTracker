import kotlinx.css.*
import styled.StyleSheet

object WelcomeStyles : StyleSheet("WelcomeStyles", isStatic = true) {
    val textContainer by css {
        padding(5.px)

        backgroundColor = rgb(8, 97, 22)
        color = rgb(56, 246, 137)
    }

    val textInput by css {
        margin(vertical = 5.px)

        fontSize = 14.px
    }

    val unknownCard by css {
        borderRadius = 15.px
        backgroundColor = rgb(150, 150, 150)
        paddingLeft = 50.px
        paddingRight = 50.px
        paddingTop = 60.px
        paddingBottom = 60.px
        marginLeft = 0.px
        marginRight = 15.px
        display = Display.inline
    }
} 
