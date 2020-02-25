package part3.lesson15;

import org.apache.log4j.PatternLayout;
import org.apache.log4j.helpers.PatternParser;
import org.apache.log4j.pattern.BridgePatternParser;

/**
 * Собственный PatternLayout для log4j.
 * Записывает исключение по паттерну {@code %throwable{1}}.
 * @autor Aleksey Danilchik
 */
public class MyPatternLayout extends PatternLayout {
    protected PatternParser createPatternParser(String pattern) {
        return new BridgePatternParser(pattern);
    }
}
