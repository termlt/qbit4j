package org.termlt.qbit4j.model.app.preferences;

public sealed interface ScanDirs {

    // 0
    record MonitoredFolder(String path) implements ScanDirs {
    }

    // 1
    record DefaultSavePath(String path) implements ScanDirs {
    }

    // "/path/to/download/to"
    record CustomPath(String watchedPath, String customPath) implements ScanDirs {
    }
}
