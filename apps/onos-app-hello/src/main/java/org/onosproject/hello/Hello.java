package org.onosproject.hello;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.onosproject.core.ApplicationId;
import org.onosproject.core.CoreService;

import org.onosproject.net.packet.PacketProcessor;
import org.onosproject.net.packet.PacketService;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;
/**
 * Hello world!
 *
 */
@Component(immediate = true)
public class Hello 
{
    private final Logger log = getLogger(getClass());
    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected PacketService packetService;

    @Reference(cardinality = ReferenceCardinality.MANDATORY_UNARY)
    protected CoreService coreService;

    private ApplicationId appId;
    private ReactivePacketProcessor processor = new ReactivePacketProcessor();

    @Activate
    public void activate(ComponentContext context) {
        appId = coreService.registerApplication("org.onosproject.hello");

        packetService.addProcessor(processor, PacketProcessor.director(2));
        log.info("Started", appId.id());
    }

    @Deactivate
    public void deactivate() {
        packetService.removeProcessor(processor);
        processor = null;
        log.info("Stopped");
    }

       /**
     * Packet processor responsible for forwarding packets along their paths.
     */
    private class ReactivePacketProcessor implements PacketProcessor {

        @Override
        public void process(PacketContext context) {
        }

    }

}
