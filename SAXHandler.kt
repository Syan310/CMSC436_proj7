import com.example.project7.Balloon
import com.example.project7.Balloons
import org.xml.sax.Attributes
import org.xml.sax.SAXException
import org.xml.sax.helpers.DefaultHandler

class SAXHandler : DefaultHandler() {
    private val balloons = Balloons()
    private var tempValue: String? = null
    private var tempX: Float? = null
    private var tempY: Float? = null
    private var tempRadius: Float? = null

    @Throws(SAXException::class)
    override fun characters(ch: CharArray, start: Int, length: Int) {
        tempValue = String(ch, start, length).trim()
    }

    @Throws(SAXException::class)
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        // Reset temporary values at the start of a "balloon" element
        if (qName.equals("balloon", ignoreCase = true)) {
            tempX = null
            tempY = null
            tempRadius = null
        }
    }

    @Throws(SAXException::class)
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        when (qName?.toLowerCase()) {
            "x" -> tempX = tempValue?.toFloatOrNull()
            "y" -> tempY = tempValue?.toFloatOrNull()
            "radius" -> tempRadius = tempValue?.toFloatOrNull()
            "balloon" -> {
                if (tempX != null && tempY != null && tempRadius != null) {
                    val balloon = Balloon(tempX!!, tempY!!, tempRadius!!)
                    balloons.addBalloon(balloon)
                }
            }
        }
    }

    fun getParsedData(): Balloons = balloons
}
