package tech.rocksavage.style

import scalafix.v1._
import scala.meta._

class NoPrintlnRule extends SemanticRule("NoPrintlnRule") {
  override def fix(implicit doc: SemanticDocument): Patch = {
    doc.tree.collect {
      // Use the new extractor for scalameta 4.6.0+
      case t @ Term.Apply.After_4_6_0(fun, _) if fun.syntax == "println" =>
        Patch.lint(Diagnostic("no-println", "Usage of 'println' is forbidden. Use a logger instead.", t.pos))
    }.asPatch
  }
}