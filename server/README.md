goldfish server
==============

Dependencies
------------

* Ruby 1.8.7
* MongoDB 2.0+
* Sinatra 1.3+

Install Dependencies
--------------------

    % bundle install


Config
------

    % cp sample.config.yaml config.yaml

edit "config.yaml".


Run
---

    % RACK_ENV=development
    # or
    % RACK_ENV=production


    % bundle exec rackup config.ru -p 5000

open http://localhost:5000
