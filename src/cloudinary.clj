(ns cloudinary
  (:require [clojure.string :as string]
            [clj-http.client :as http]
            [pandect.algo.sha1 :refer [sha1]]))

(defn api-url [cloud-name & path]
  (string/join "/" (into ["https://api.cloudinary.com/v1_1" cloud-name]
                         path)))

(defn signature [opts]
  (sha1 (str (->> (dissoc opts :api_key :api_secret :cloud_name)
                  (sort-by first)
                  (map (fn [[k v]] (str (name k) "=" v)))
                  (string/join "&"))
             (:api_secret opts))))

(defn upload
  ([file-type file {:keys [api_key api_secret cloud_name] :as opts}]
   (assert (every? seq [api_key api_secret cloud_name]))
   (let [timestamp (quot (System/currentTimeMillis) 1000)
         opts (assoc opts :timestamp timestamp)]
     (http/post (api-url cloud_name file-type "upload")
                {:form-params (assoc opts
                                     :signature (signature opts)
                                     :file file)})))
  ([file opts]
   (upload "auto" file opts)))
