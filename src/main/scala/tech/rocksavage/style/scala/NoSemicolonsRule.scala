package tech.rocksavage.style.scala

import scalafix.v1._
import scala.meta._
import scala.meta.tokens.Token

class NoSemicolonsRule extends SyntacticRule("NoSemicolonsRule") {
  override def fix(implicit doc: SyntacticDocument): Patch =
    doc.tokens.collect {
      case semicolon: Token.Semicolon =>
        Patch.lint(
          Diagnostic(
            "no-semicolon",
            "Semicolons are discouraged. Remove this `;`.",
            semicolon.pos
          )
        )
    }.asPatch
}