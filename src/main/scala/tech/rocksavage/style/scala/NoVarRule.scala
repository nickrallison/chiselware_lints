package tech.rocksavage.style.scala

import scalafix.v1._

import scala.meta._

class NoVarRule extends SyntacticRule("NoVarRule") {
  override def fix(implicit doc: SyntacticDocument): Patch = {
    doc.tree.collect {
      case v: Defn.Var =>
        Patch.lint(Diagnostic("no-var", "Usage of 'var' is forbidden. Use 'val' instead.", v.pos))
    }.asPatch
  }
}