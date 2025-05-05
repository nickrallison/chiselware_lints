package tech.rocksavage.style.scala

import scalafix.v1._
import scala.meta._

class ExplicitResultTypesRule extends SyntacticRule("ExplicitResultTypesRule") {
  override def fix(implicit doc: SyntacticDocument): Patch =
    doc.tree.collect {
      case d @ Defn.Def(_, name, _, _, None, _)
        if !d.mods.exists(_.is[Mod.Implicit]) =>
        Patch.lint(
          Diagnostic(
            "explicit-result-types",
            s"Method `${name.value}` is missing an explicit result type.",
            name.pos
          )
        )
    }.asPatch
}