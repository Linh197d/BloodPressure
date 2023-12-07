package blood.pressure.fingerprint.scanner.bpmonitor.util

import java.io.IOException
import java.io.PrintWriter
import java.io.Writer

/**
 * A very simple CSV reader released under a commercial-friendly license.
 *
 * @author Glen Smith
 */
class CSVWriter @JvmOverloads constructor(
    writer: Writer?,
    separator: Char = DEFAULT_SEPARATOR,
    quotechar: Char = DEFAULT_QUOTE_CHARACTER,
    escapechar: Char =
        DEFAULT_ESCAPE_CHARACTER,
    lineEnd: String = DEFAULT_LINE_END
) {
    private val pw: PrintWriter
    private val separator: Char
    private val quotechar: Char
    private val escapechar: Char
    private val lineEnd: String

    /**
     * Writes the next line to the file.
     *
     * @param nextLine
     * a string array with each comma-separated element as a separate
     * entry.
     */
    fun writeNext(nextLine: Array<String>) {
        val sb = StringBuffer()
        for (i in nextLine.indices) {
            if (i != 0) {
                sb.append(separator)
            }
            val nextElement = nextLine[i]
            if (quotechar != NO_QUOTE_CHARACTER) sb.append(quotechar)
            for (element in nextElement) {
                if (escapechar != NO_ESCAPE_CHARACTER && element == quotechar) {
                    sb.append(escapechar).append(element)
                } else if (escapechar != NO_ESCAPE_CHARACTER && element == escapechar) {
                    sb.append(escapechar).append(element)
                } else {
                    sb.append(element)
                }
            }
            if (quotechar != NO_QUOTE_CHARACTER) sb.append(quotechar)
        }
        sb.append(lineEnd)
        pw.write(sb.toString())
    }

    /**
     * Flush underlying stream to writer.
     *
     * @throws IOException if bad things happen
     */
    @Throws(IOException::class)
    fun flush() {
        pw.flush()
    }

    /**
     * Close the underlying stream writer flushing any buffered content.
     *
     * @throws IOException if bad things happen
     */
    @Throws(IOException::class)
    fun close() {
        pw.flush()
        pw.close()
    }

    companion object {
        /** The character used for escaping quotes.  */
        const val DEFAULT_ESCAPE_CHARACTER = '"'

        /** The default separator to use if none is supplied to the constructor.  */
        const val DEFAULT_SEPARATOR = ','

        /**
         * The default quote character to use if none is supplied to the
         * constructor.
         */
        const val DEFAULT_QUOTE_CHARACTER = '"'

        /** The quote constant to use when you wish to suppress all quoting.  */
        const val NO_QUOTE_CHARACTER = '\u0000'

        /** The escape constant to use when you wish to suppress all escaping.  */
        const val NO_ESCAPE_CHARACTER = '\u0000'

        /** Default line terminator uses platform encoding.  */
        const val DEFAULT_LINE_END = "\n"
    }
    /**
     * Constructs CSVWriter with supplied separator, quote char, escape char and line ending.
     *
     * @param writer
     * the writer to an underlying CSV source.
     * @param separator
     * the delimiter to use for separating entries
     * @param quotechar
     * the character to use for quoted elements
     * @param escapechar
     * the character to use for escaping quotechars or escapechars
     * @param lineEnd
     * the line feed terminator to use
     */
    /**
     * Constructs CSVWriter using a comma for the separator.
     *
     * @param writer
     * the writer to an underlying CSV source.
     */
    init {
        pw = PrintWriter(writer)
        this.separator = separator
        this.quotechar = quotechar
        this.escapechar = escapechar
        this.lineEnd = lineEnd
    }
}