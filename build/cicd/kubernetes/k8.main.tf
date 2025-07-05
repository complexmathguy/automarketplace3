
provider "kubernetes" {
  host = ""
  username = var.kubernetes-username
  password = var.kubernetes-password
  version = "~> 1.10"
}

#Declare_K8_Pods()
#Declare_K8_Services()