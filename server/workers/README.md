GoldFish sample workers
=======================

TCP Chat sample
---------------

Install Dependencies

    % gem install ArgsParser eventmachine

run

    % ruby server/workers/chat-server -h
    % ruby server/workers/chat-server -p 8082


UDP Mouse/Touchpad sample
-------------------------

Install Dependencies

    % brew install jruby
    % brew use jruby
    % gem install ArgsParser
    % gem install eventmachine

run

    % jruby server/workers/udp-mouse.rb -h
    % jruby server/workers/udp-mouse.rb -p 8083
