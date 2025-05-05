package tech.rocksavage.style.scala

import scalafix.v1._
import scala.meta._

class CamelCaseMethodNamesRule extends SyntacticRule("CamelCaseMethodNamesRule") {
  private val camelLower = "^[a-z][A-Za-z0-9]*$".r
  override def fix(implicit doc: SyntacticDocument): Patch =
    doc.tree.collect {
      case d @ Defn.Def(_, name, _, _, _, _)
        if camelLower.findFirstIn(name.value).isEmpty =>
        Patch.lint(
          Diagnostic(
            "method-naming",
            s"Method `${name.value}` should be in camelCase, starting with a lowercase letter.",
            name.pos
          )
        )
    }.asPatch
}