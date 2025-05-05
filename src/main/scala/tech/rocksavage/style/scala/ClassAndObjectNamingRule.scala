package tech.rocksavage.style.scala

import scalafix.v1._
import scala.meta._

class ClassAndObjectNamingRule
  extends SyntacticRule("ClassAndObjectNamingRule") {
  private val pascal = "^[A-Z][A-Za-z0-9]*$".r
  override def fix(implicit doc: SyntacticDocument): Patch = {
    val classViolations = doc.tree.collect {
      case c: Defn.Class if pascal.findFirstIn(c.name.value).isEmpty =>
        Patch.lint(
          Diagnostic(
            "class-naming",
            s"Class name `${c.name.value}` should start with an uppercase letter.",
            c.name.pos
          )
        )
    }
    val objectViolations = doc.tree.collect {
      case o: Defn.Object if pascal.findFirstIn(o.name.value).isEmpty =>
        Patch.lint(
          Diagnostic(
            "object-naming",
            s"Object `${o.name.value}` should start with an uppercase letter.",
            o.name.pos
          )
        )
    }
    (classViolations ++ objectViolations).asPatch
  }
}