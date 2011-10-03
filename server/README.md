player
======

Install Dependencies
--------------------

    % gem install sinatra thin sinatra-reloader json haml bundler mongoid bson bson_ext


or


    % gem install bundler
    % bundle install
    

Run
---

    % ruby development.ru

open [http://localhost:8125](http://localhost:8125)


Deploy
------
use Passenger with "config.ru"
