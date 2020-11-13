import kotlinx.css.*
import styled.StyleSheet

object WelcomeStyles : StyleSheet("WelcomeStyles", isStatic = true) {

    val card by css {
        media("screen and (min-width: 768px)") {
            borderRadius = 15.px
            paddingLeft = 50.px
            paddingRight = 50.px
            paddingTop = 60.px
            paddingBottom = 60.px
            marginLeft = 0.px
        }
        media("screen and (max-width: 767px)") {
            borderRadius = 10.px
            paddingLeft = 35.px
            paddingRight = 35.px
            paddingTop = 40.px
            paddingBottom = 40.px
            marginLeft = 0.px
        }
        marginRight = 8.px
        display = Display.block
        alignContent = Align.center
        justifyContent = JustifyContent.center
    }

    val cardText by css {
        display = Display.inlineFlex
        alignContent = Align.center
        justifyContent = JustifyContent.center
        media("screen and (min-width: 768px)") {
            fontSize = 30.px
        }
        media("screen and (max-width: 767px)") {
            fontSize = 22.px
        }
    }

    val unknownCard by css {
        backgroundColor = rgb(150, 150, 150)
    }

    val chosenCard by css {
        backgroundColor = rgb(180, 180, 220)

        media("screen and (max-width: 767px)") {
            border = "6px solid teal"

            paddingLeft = 29.px
            paddingRight = 29.px
            paddingTop = 34.px
            paddingBottom = 34.px
        }
        media("screen and (min-width: 768px)") {
            border = "10px solid teal"

            paddingLeft = 40.px
            paddingRight = 40.px
            paddingTop = 50.px
            paddingBottom = 50.px
        }
    }
} 
