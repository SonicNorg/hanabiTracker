import kotlinx.css.*
import styled.StyleSheet

object WelcomeStyles : StyleSheet("WelcomeStyles", isStatic = true) {

    val card by css {
        borderRadius = 15.px
        paddingLeft = 50.px
        paddingRight = 50.px
        paddingTop = 60.px
        paddingBottom = 60.px
        marginLeft = 0.px
        marginRight = 8.px
        display = Display.block
        alignContent = Align.center
        justifyContent = JustifyContent.center
    }

    val cardText by css {
        display = Display.inlineFlex
        alignContent = Align.center
        justifyContent = JustifyContent.center
        fontSize = 30.px
    }

    val unknownCard by css {
        backgroundColor = rgb(150, 150, 150)
    }

    val chosenCard by css {
        backgroundColor = rgb(180, 180, 220)
        border = "10px solid teal"

        paddingLeft = 40.px
        paddingRight = 40.px
        paddingTop = 50.px
        paddingBottom = 50.px
    }
} 
