resource "kubernetes_replication_controller" "app-master" {
  metadata {
    name = "app-master"
  }

  spec {
    replicas = 1

    selector = {
      app  = "demo"
    }
    template {        
    template {
      container {
        image = "DockerComposeDBImage"
        name  = "db-container"

        port {
          container_port = 8082:8082        }

        resources {
          requests {
            cpu    = "100m"
            memory = "100Mi"
          }
        }

        
      container {
        image = "/:latest
"
        name  = "app-container"

        port {
          container_port = :        }
        env {
          name  = "MYSQL_DATABASE"
          value = "developmentdb"
        }
        env {
          name  = "MYSQL_ROOT_PASSWORD"
          value = "letmein2"
        }
        env {
          name  = "MYSQL_DATABASE"
          value = "developmentdb"
        }
        env {
          name  = "MYSQL_ROOT_PASSWORD"
          value = "letmein2"
        }
        resources {
          requests {
            cpu    = "100m"
            memory = "100Mi"
          }
        }

      }

    }
    }
  }
}