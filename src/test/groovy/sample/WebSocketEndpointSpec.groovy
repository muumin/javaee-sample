package sample

import spock.lang.Specification

import javax.websocket.RemoteEndpoint
import javax.websocket.Session

class WebSocketEndpointSpec extends Specification {
    WebSocketEndpoint endpoint

    void setup() {
        endpoint = new WebSocketEndpoint()
    }

    def "Open/Close"() {
        setup :
        def session = Mock(Session)

        when:
        endpoint.open(session)

        then:
        endpoint.sessionSet.size() == 1
        endpoint.sessionSet[0] == session

        when:
        endpoint.close(session)

        then:
        endpoint.sessionSet.size() == 0
    }

    def "OnMessage"() {
        setup:
        def session = Mock(Session) {
            1 * getAsyncRemote() >> Mock(RemoteEndpoint.Async) {
                1 * sendText("test message.")
            }
        }
        endpoint.open(session)

        expect:
        endpoint.onMessage("test message.")
    }
}
