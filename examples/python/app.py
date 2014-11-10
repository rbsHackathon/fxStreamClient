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
    for quoteKey in decoded['fx']:
        quote = decoded['fx'][quoteKey]
        print "%s buy %.5f sell %.5f" % (quoteKey, quote['buy'], quote['sell'])

def connect_handler(data):
    print "connected"
    channel = pusher.subscribe("fxRateStream.dev")
    channel.bind('fxEvent.dev', channel_callback)

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
