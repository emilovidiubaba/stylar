package com.zeoflow.stylar.codestyle.languages;

import com.zeoflow.stylar.codestyle.CodeStyle;

import org.jetbrains.annotations.NotNull;

import static com.zeoflow.stylar.codestyle.CodeStyle.grammar;
import static com.zeoflow.stylar.codestyle.CodeStyle.pattern;
import static com.zeoflow.stylar.codestyle.CodeStyle.token;
import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static java.util.regex.Pattern.MULTILINE;
import static java.util.regex.Pattern.compile;

public class CodeStyle_yaml
{

  @NotNull
  public static CodeStyle.Grammar create(@NotNull CodeStyle codeStyle)
  {
    return grammar("yaml",
      token("scalar", pattern(
        compile("([\\-:]\\s*(?:![^\\s]+)?[ \\t]*[|>])[ \\t]*(?:((?:\\r?\\n|\\r)[ \\t]+)[^\\r\\n]+(?:\\2[^\\r\\n]+)*)"),
        true,
        false,
        "string"
      )),
      token("comment", pattern(compile("#.*"))),
      token("key", pattern(
        compile("(\\s*(?:^|[:\\-,\\[{\\r\\n?])[ \\t]*(?:![^\\s]+)?[ \\t]*)[^\\r\\n{\\[\\]},#\\s]+?(?=\\s*:\\s)"),
        true,
        false,
        "atrule"
      )),
      token("directive", pattern(
        compile("(^[ \\t]*)%.+", MULTILINE),
        true,
        false,
        "important"
      )),
      token("datetime", pattern(
        compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:\\d{4}-\\d\\d?-\\d\\d?(?:[tT]|[ \\t]+)\\d\\d?:\\d{2}:\\d{2}(?:\\.\\d*)?[ \\t]*(?:Z|[-+]\\d\\d?(?::\\d{2})?)?|\\d{4}-\\d{2}-\\d{2}|\\d\\d?:\\d{2}(?::\\d{2}(?:\\.\\d*)?)?)(?=[ \\t]*(?:$|,|]|\\}))", MULTILINE),
        true,
        false,
        "number"
      )),
      token("boolean", pattern(
        compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:true|false)[ \\t]*(?=$|,|]|\\})", MULTILINE | CASE_INSENSITIVE),
        true,
        false,
        "important"
      )),
      token("null", pattern(
        compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(?:null|~)[ \\t]*(?=$|,|]|\\})", MULTILINE | CASE_INSENSITIVE),
        true,
        false,
        "important"
      )),
      token("string", pattern(
        compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)(\"|')(?:(?!\\2)[^\\\\\\r\\n]|\\\\.)*\\2(?=[ \\t]*(?:$|,|]|\\}))", MULTILINE),
        true,
        true
      )),
      token("number", pattern(
        compile("([:\\-,\\[{]\\s*(?:![^\\s]+)?[ \\t]*)[+-]?(?:0x[\\da-f]+|0o[0-7]+|(?:\\d+\\.?\\d*|\\.?\\d+)(?:e[+-]?\\d+)?|\\.inf|\\.nan)[ \\t]*(?=$|,|]|\\})", MULTILINE | CASE_INSENSITIVE),
        true
      )),
      token("tag", pattern(compile("![^\\s]+"))),
      token("important", pattern(compile("[&*][\\w]+"))),
      token("punctuation", pattern(compile("---|[:\\[\\]{}\\-,|>?]|\\.\\.\\.")))
    );
  }
}
