#gem install pusher-client

require 'pusher-client'
socket = PusherClient::Socket.new('ec68fe8cadf14e52b659')
socket.connect(true) # Connect asynchronously

socket.subscribe('fxRateStream')

# Bind to a channel event (can only occur on channel1)
socket['fxRateStream'].bind('fxEvent') do |data|
  puts data
end

loop do
  sleep(1) # Keep your main thread running
end
