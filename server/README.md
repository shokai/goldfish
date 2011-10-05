gynamic server
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

    % ruby development.ru

open [http://localhost:8280](http://localhost:8280)


Deploy
------
use Passenger with "config.ru"
