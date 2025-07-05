resource "kubernetes_service" "app-master" {
  metadata {
    name = "app-master"
  }

  spec {
    selector = {
      app  = "demo"
    }
    port {
      name        = "app-port"
      port        = :      target_port = :    }

    port {
      name        = "db-port"
      port        = 8082:8082      target_port = 8082:8082    }

    port {
      name        = "app-port"
      port        = :      target_port = :    }


    type = ""
  }
  
}
