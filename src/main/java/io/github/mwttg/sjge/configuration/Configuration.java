package io.github.mwttg.sjge.configuration;

public record Configuration(GameWindowConfiguration gameWindowConfiguration,
                            OpenGlConfiguration openGlConfiguration,
                            ViewPortConfiguration viewPortConfiguration) {

  public String prettyPrint() {
    return """
        Configuration
        %s%s%s
        """.formatted(
        gameWindowConfiguration.prettyPrint(),
        openGlConfiguration.prettyPrint(),
        viewPortConfiguration.prettyPrint());
  }
}
