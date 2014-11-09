#!/usr/bin/env python

import sys
sys.path.append('..')

import logging
import time
import pusherclient
import json
from pusherclient import Connection
global pusher

def channel_callback(data):
    print "----------------------------------------------"
    decoded = json.loads(data)
    for quote in decoded: 
        print "%s/%s:%.5f" % (quote['buy'], quote['sell'], quote['rate'])

def connect_handler(data):
    print "connected"
    channel = pusher.subscribe("fxRateStream")
    channel.bind('fxEvent', channel_callback)
    
if __name__ == '__main__':
    logging.basicConfig()
    print "starting up"
    pusher = pusherclient.Pusher("ec68fe8cadf14e52b659", True, None, None, logging.ERROR, True)
    pusher.connection.bind('pusher:connection_established', connect_handler)
    print "connecting"
    pusher.connect()
    print "waiting"
    while True:
        time.sleep(1)