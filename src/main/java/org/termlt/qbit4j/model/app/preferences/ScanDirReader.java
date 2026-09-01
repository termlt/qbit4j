package org.termlt.qbit4j.model.app.preferences;

import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ScanDirReader implements ObjectReader<List<ScanDirs>> {

    @Override
    public List<ScanDirs> readObject(JSONReader input, Type fieldType, Object fieldName, long features) {
        List<ScanDirs> scanDirs = new ArrayList<>();

        input.nextIfObjectStart();

        while (!input.nextIfObjectEnd()) {
            String watchedPath = input.readFieldName();

            if (input.isString()) {
                scanDirs.add(new ScanDirs.CustomPath(watchedPath, input.readString()));
            } else {
                byte num = input.readInt8();
                switch (num) {
                    case 0 -> scanDirs.add(new ScanDirs.MonitoredFolder(watchedPath));
                    case 1 -> scanDirs.add(new ScanDirs.DefaultSavePath(watchedPath));
                }
            }
        }

        return scanDirs;
    }
}
