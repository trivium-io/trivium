package io.trivium.anystore;

import io.trivium.Central;
import io.trivium.anystore.query.Query;
import io.trivium.extension._2a4a0814f16c4f2b8c9ab1f51289b00c.Differential;
import io.trivium.glue.TriviumObject;
import io.trivium.profile.DataPoints;
import io.trivium.profile.Profiler;
import io.trivium.profile.Ticker;
import javolution.util.FastList;
import net.openhft.chronicle.ExcerptAppender;
import net.openhft.chronicle.IndexedChronicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class AnyClient {

	public static AnyClient INSTANCE = new AnyClient();
	private ExcerptAppender pipeIn;
    Logger log = LogManager.getLogger(getClass());

	public AnyClient() {
		String locPipeIn = Central.getProperty("basePath") + "queues"
				+ File.separator + "anystore" + File.separator + "queueIn";

        IndexedChronicle chronicle = null;
        try {
            chronicle = new IndexedChronicle(locPipeIn);
			pipeIn = chronicle.createAppender();
		} catch (IOException e) {
			log.error(e);
		}
        //init profiler
        Profiler.INSTANCE.initTicker(new Ticker(DataPoints.ANYSTORE_QUEUE_IN));
        Profiler.INSTANCE.initDifferential(new Differential(DataPoints.ANYSTORE_QUEUE_SIZE));
    }

    public synchronized void storeObject(TriviumObject po) {
        Profiler.INSTANCE.tick(DataPoints.ANYSTORE_QUEUE_IN);
        Profiler.INSTANCE.increment(DataPoints.ANYSTORE_QUEUE_SIZE);
        //pre serialize
        byte[] id = po.getId().toBytes();
        byte[] typeId = po.getTypeId().toBytes();
        byte[] metadata = po.getMetadataBinary();
        byte[] data = po.getDataBinary();

        int len = id.length + typeId.length + 4 + metadata.length + 4 + data.length;

        pipeIn.startExcerpt(len);
        pipeIn.write(id);
        pipeIn.write(typeId);
        pipeIn.writeInt(metadata.length);
        pipeIn.write(metadata);
        pipeIn.writeInt(data.length);
        pipeIn.write(data);
        pipeIn.finish();
    }

	public void delete(Query query) {
        AnyServer.INSTANCE.getStore().delete(query);
	}

	public FastList<TriviumObject> loadObjects(Query query) {
		return AnyServer.INSTANCE.getStore().loadObjects(query);
	}
}
