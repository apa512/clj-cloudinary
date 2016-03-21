(ns cloudinary.admin
  (:require [clojure.string :as string]
            [clj-http.client :as http]
            [cloudinary]))

(defn api-url [cloud-name & path]
  (string/join "/" (into ["https://api.cloudinary.com/v1_1" cloud-name]
                         path)))

(defn delete-all-resources
  ([file-type type {:keys [api_key api_secret cloud_name] :as opts}]
   (http/delete (api-url cloud_name "resources" file-type type)
                {:basic-auth [api_key api_secret]
                 :query-params {:all true}})))
