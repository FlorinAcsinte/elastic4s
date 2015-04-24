package com.sksamuel.elastic4s

import org.elasticsearch.action.admin.indices.recovery.{ RecoveryRequest, RecoveryResponse }
import org.elasticsearch.client.Client

import scala.concurrent.Future

trait IndexRecoveryDsl {

  implicit object IndexRecoveryDefinitionExecutable
      extends Executable[IndexRecoveryDefinition, RecoveryResponse] {
    override def apply(c: Client, t: IndexRecoveryDefinition): Future[RecoveryResponse] = {
      injectFuture(c.admin.indices.recoveries(t.build, _))
    }
  }
}

class IndexRecoveryDefinition(indices: Seq[String]) {
  private def builder = new RecoveryRequest(indices: _*)
  def build = builder
}
