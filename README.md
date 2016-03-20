# clj-cloudinary

A client designed to interact with the Cloudinary service.

## Usage
```clojure
(require '[cloudinary :as c])

(c/upload "http://i.imgur.com/KEHurbt.jpg"
          {:api_key "my_key"
           :api_secret "my_secret"
           :cloud_name "my_cloud"})
```

## License

Copyright © 2016 Erik Strömberg

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
