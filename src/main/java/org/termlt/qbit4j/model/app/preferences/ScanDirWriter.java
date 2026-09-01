package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;
import java.util.List;

public class ScanDirWriter implements ObjectWriter<List<ScanDirs>> {

    @Override
    public void write(JSONWriter output, Object object, Object fieldName, Type fieldType, long features) {

        @SuppressWarnings("unchecked")
        List<ScanDirs> scanDirs = (List<ScanDirs>) object;

        output.startObject();

        for (ScanDirs scanDir : scanDirs) {
            switch (scanDir) {
                case ScanDirs.MonitoredFolder m -> output.writeNameValue(m.path(), 0);
                case ScanDirs.DefaultSavePath d -> output.writeNameValue(d.path(), 1);
                case ScanDirs.CustomPath c -> output.writeNameValue(c.watchedPath(), c.customPath());
            }
        }

        output.endObject();
    }
}
