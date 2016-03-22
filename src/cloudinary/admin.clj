(ns cloudinary.admin
  (:require [clojure.string :as string]
            [clj-http.client :as http]
            [cloudinary]))

(defn delete-all-resources
  ([file-type type {:keys [api_key api_secret cloud_name] :as opts}]
   (http/delete (cloudinary/api-url cloud_name "resources" file-type type)
                {:basic-auth [api_key api_secret]
                 :query-params {:all true}}))
  ([file-type opts]
   (delete-all-resources file-type "upload" opts))
  ([opts]
   (delete-all-resources "image" "upload" opts)))
