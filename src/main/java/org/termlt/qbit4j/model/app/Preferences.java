package org.termlt.qbit4j.model.app;

import com.alibaba.fastjson2.annotation.JSONField;
import org.termlt.qbit4j.model.app.preferences.*;

import java.util.List;

public record Preferences(

        // Currently selected language (e.g. en_GB for English)
        String locale,

        // True if a subfolder should be created when adding a torrent
        @JSONField(name = "create_subfolder_enabled") boolean createSubfolderEnabled,

        // True if torrents should be added in a Paused state
        @JSONField(name = "start_paused_enabled") boolean startPausedEnabled,

        //
        @JSONField(name = "auto_delete_mode") int autoDeleteMode,

        // True if disk space should be pre-allocated for all files
        @JSONField(name = "preallocate_all") boolean preallocateAll,

        // True if ".!qB" should be appended to incomplete files
        @JSONField(name = "incomplete_files_ext") boolean incompleteFilesExt,

        // True if Automatic Torrent Management is enabled by default
        @JSONField(name = "auto_tmm_enabled") boolean autoTmmEnabled,

        // True if torrent should be relocated when its Category changes
        @JSONField(name = "torrent_changed_tmm_enabled") boolean torrentChangedTmmEnabled,

        // True if torrent should be relocated when the default save path changes
        @JSONField(name = "save_path_changed_tmm_enabled") boolean savePathChangedTmmEnabled,

        // True if torrent should be relocated when its Category's save path changes
        @JSONField(name = "category_changed_tmm_enabled") boolean categoryChangedTmmEnabled,

        // Default save path for torrents, separated by slashes
        @JSONField(name = "save_path") String savePath,

        // True if folder for incomplete torrents is enabled
        @JSONField(name = "temp_path_enabled") boolean tempPathEnabled,

        // Path for incomplete torrents, separated by slashes
        @JSONField(name = "temp_path") String tempPath,

        // Property: directory to watch for torrent files, value: where torrents loaded from this directory should be downloaded to.
        // Slashes are used as path separators; multiple key/value pairs can be specified
        @JSONField(name = "scan_dirs", deserializeUsing = ScanDirReader.class, serializeUsing = ScanDirWriter.class)
        List<ScanDirs> scanDirs,

        // Path to directory to copy .torrent files to. Slashes are used as path separators
        @JSONField(name = "export_dir") String exportDir,

        // Path to directory to copy .torrent files of completed downloads to. Slashes are used as path separators
        @JSONField(name = "export_dir_fin") String exportDirFin,

        // True if e-mail notification should be enabled
        @JSONField(name = "mail_notification_enabled") boolean mailNotificationEnabled,

        // e-mail where notifications should originate from
        @JSONField(name = "mail_notification_sender") String mailNotificationSender,

        // e-mail to send notifications to
        @JSONField(name = "mail_notification_email") String mailNotificationEmail,

        // smtp server for e-mail notifications
        @JSONField(name = "mail_notification_smtp") String mailNotificationSmtp,

        // True if smtp server requires SSL connection
        @JSONField(name = "mail_notification_ssl_enabled") boolean mailNotificationSslEnabled,

        // True if smtp server requires authentication
        @JSONField(name = "mail_notification_auth_enabled") boolean mailNotificationAuthEnabled,

        // Username for smtp authentication
        @JSONField(name = "mail_notification_username") String mailNotificationUsername,

        // Password for smtp authentication
        @JSONField(name = "mail_notification_password") String mailNotificationPassword,

        // True if external program should be run after torrent has finished downloading
        @JSONField(name = "autorun_enabled") boolean autorunEnabled,

        // Program path/name/arguments to run if autorun_enabled is enabled; path is separated by slashes; you can use %f and %n arguments, which will be expanded by qBittorrent as path_to_torrent_file and torrent_name (from the GUI; not the .torrent file name) respectively
        @JSONField(name = "autorun_program") String autorunProgram,

        // True if torrent queuing is enabled
        @JSONField(name = "queueing_enabled") boolean queueingEnabled,

        // Maximum number of active simultaneous downloads
        @JSONField(name = "max_active_downloads") int maxActiveDownloads,

        // Maximum number of active simultaneous downloads and uploads
        @JSONField(name = "max_active_torrents") int maxActiveTorrents,

        // Maximum number of active simultaneous uploads
        @JSONField(name = "max_active_uploads") int maxActiveUploads,

        // If true torrents w/o any activity (stalled ones) will not be counted towards max_active_* limits; see dont_count_slow_torrents for more information
        @JSONField(name = "dont_count_slow_torrents") boolean dontCountSlowTorrents,

        // Download rate in KiB/s for a torrent to be considered "slow"
        @JSONField(name = "slow_torrent_dl_rate_threshold") int slowTorrentDlRateThreshold,

        // Upload rate in KiB/s for a torrent to be considered "slow"
        @JSONField(name = "slow_torrent_ul_rate_threshold") int slowTorrentUlRateThreshold,

        // Seconds a torrent should be inactive before considered "slow"
        @JSONField(name = "slow_torrent_inactive_timer") int slowTorrentInactiveTimer,

        // True if share ratio limit is enabled
        @JSONField(name = "max_ratio_enabled") boolean maxRatioEnabled,

        // Get the global share ratio limit
        @JSONField(name = "max_ratio") double maxRatio,

        // Action performed when a torrent reaches the maximum share ratio.
        @JSONField(name = "max_ratio_act") MaxRatioAct maxRatioAct,

        // Port for incoming connections
        @JSONField(name = "listen_port") int listenPort,

        // True if UPnP/NAT-PMP is enabled
        boolean upnp,

        // True if the port is randomly selected
        @JSONField(name = "random_port") boolean randomPort,

        // Global download speed limit in KiB/s; -1 means no limit is applied
        @JSONField(name = "dl_limit") int dlLimit,

        // Global upload speed limit in KiB/s; -1 means no limit is applied
        @JSONField(name = "up_limit") int upLimit,

        // Maximum global number of simultaneous connections
        @JSONField(name = "max_connec") int maxConnec,

        // Maximum number of simultaneous connections per torrent
        @JSONField(name = "max_connec_per_torrent") int maxConnecPerTorrent,

        // Maximum number of upload slots
        @JSONField(name = "max_uploads") int maxUploads,

        // Maximum number of upload slots per torrent
        @JSONField(name = "max_uploads_per_torrent") int maxUploadsPerTorrent,

        // Timeout in seconds for a stopped announce request to trackers
        @JSONField(name = "stop_tracker_timeout") int stopTrackerTimeout,

        // True if the advanced libtorrent option piece_extent_affinity is enabled
        @JSONField(name = "enable_piece_extent_affinity") boolean enablePieceExtentAffinity,

        // Bittorrent Protocol to use
        @JSONField(name = "bittorrent_protocol") BittorrentProtocol bittorrentProtocol,

        // True if [du]l_limit should be applied to uTP connections; this option is only available in qBittorrent built against libtorrent version 0.16.X and higher
        @JSONField(name = "limit_utp_rate") boolean limitUtpRate,

        // True if [du]l_limit should be applied to estimated TCP overhead (service data: e.g. packet headers)
        @JSONField(name = "limit_tcp_overhead") boolean limitTcpOverhead,

        // True if [du]l_limit should be applied to peers on the LAN
        @JSONField(name = "limit_lan_peers") boolean limitLanPeers,

        // Alternative global download speed limit in KiB/s
        @JSONField(name = "alt_dl_limit") int altDlLimit,

        // Alternative global upload speed limit in KiB/s
        @JSONField(name = "alt_up_limit") int altUpLimit,

        // True if alternative limits should be applied according to schedule
        @JSONField(name = "scheduler_enabled") boolean schedulerEnabled,

        // Scheduler starting hour
        @JSONField(name = "schedule_from_hour") int scheduleFromHour,

        // Scheduler starting minute
        @JSONField(name = "schedule_from_min") int scheduleFromMin,

        // Scheduler ending hour
        @JSONField(name = "schedule_to_hour") int scheduleToHour,

        // Scheduler ending minute
        @JSONField(name = "schedule_to_min") int scheduleToMin,

        // Scheduler days.
        @JSONField(name = "scheduler_days") SchedulerDays schedulerDays,

        // True if DHT is enabled
        boolean dht,

        // True if PeX is enabled
        boolean pex,

        // True if LSD is enabled
        boolean lsd,

        Encryption encryption,

        // If true anonymous mode will be enabled; read more here; this option is only available in qBittorrent built against libtorrent version 0.16.X and higher
        @JSONField(name = "anonymous_mode") boolean anonymousMode,

        @JSONField(name = "proxy_type") ProxyType proxyType,

        // Proxy IP address or domain name
        @JSONField(name = "proxy_ip") String proxyIp,

        // Proxy port
        @JSONField(name = "proxy_port") int proxyPort,

        // True if peer and web seed connections should be proxified; this option will have any effect only in qBittorrent built against libtorrent version 0.16.X and higher
        @JSONField(name = "proxy_peer_connections") boolean proxyPeerConnections,

        // True proxy requires authentication; doesn't apply to SOCKS4 proxies
        @JSONField(name = "proxy_auth_enabled") boolean proxyAuthEnabled,

        // Username for proxy authentication
        @JSONField(name = "proxy_username") String proxyUsername,

        // Password for proxy authentication
        @JSONField(name = "proxy_password") String proxyPassword,

        // True if proxy is only used for torrents
        @JSONField(name = "proxy_torrents_only") boolean proxyTorrentsOnly,

        // True if external IP filter should be enabled
        @JSONField(name = "ip_filter_enabled") boolean ipFilterEnabled,

        // Path to IP filter file (.dat, .p2p, .p2b files are supported); path is separated by slashes
        @JSONField(name = "ip_filter_path") String ipFilterPath,

        // True if IP filters are applied to trackers
        @JSONField(name = "ip_filter_trackers") boolean ipFilterTrackers,

        // Semicolon-separated list of domains to accept when performing Host header validation
        @JSONField(name = "web_ui_domain_list") String webUiDomainList,

        // IP address to use for the WebUI
        @JSONField(name = "web_ui_address") String webUiAddress,

        // WebUI port
        @JSONField(name = "web_ui_port") int webUiPort,

        // True if UPnP is used for the WebUI port
        @JSONField(name = "web_ui_upnp") boolean webUiUpnp,

        // WebUI username
        @JSONField(name = "web_ui_username") String webUiUsername,

        // For API ≥ v2.3.0: Plaintext WebUI password, not readable, write-only. For API < v2.3.0: MD5 hash of WebUI password, hash is generated from the following string: username:Web UI Access:plain_text_web_ui_password
        @JSONField(name = "web_ui_password") String webUiPassword,

        // True if WebUI CSRF protection is enabled
        @JSONField(name = "web_ui_csrf_protection_enabled") boolean webUiCsrfProtectionEnabled,

        // True if WebUI clickjacking protection is enabled
        @JSONField(name = "web_ui_clickjacking_protection_enabled") boolean webUiClickjackingProtectionEnabled,

        // True if WebUI cookie Secure flag is enabled
        @JSONField(name = "web_ui_secure_cookie_enabled") boolean webUiSecureCookieEnabled,

        // Maximum number of authentication failures before WebUI access ban
        @JSONField(name = "web_ui_max_auth_fail_count") int webUiMaxAuthFailCount,

        // WebUI access ban duration in seconds
        @JSONField(name = "web_ui_ban_duration") int webUiBanDuration,

        // Seconds until WebUI is automatically signed off
        @JSONField(name = "web_ui_session_timeout") int webUiSessionTimeout,

        // True if WebUI host header validation is enabled
        @JSONField(name = "web_ui_host_header_validation_enabled") boolean webUiHostHeaderValidationEnabled,

        // True if authentication challenge for loopback address (127.0.0.1) should be disabled
        @JSONField(name = "bypass_local_auth") boolean bypassLocalAuth,

        // True if webui authentication should be bypassed for clients whose ip resides within (at least) one of the subnets on the whitelist
        @JSONField(name = "bypass_auth_subnet_whitelist_enabled") boolean bypassAuthSubnetWhitelistEnabled,

        // (White)list of ipv4/ipv6 subnets for which webui authentication should be bypassed; list entries are separated by commas
        @JSONField(name = "bypass_auth_subnet_whitelist") String bypassAuthSubnetWhitelist,

        // True if an alternative WebUI should be used
        @JSONField(name = "alternative_webui_enabled") boolean alternativeWebuiEnabled,

        // File path to the alternative WebUI
        @JSONField(name = "alternative_webui_path") String alternativeWebuiPath,

        // True if WebUI HTTPS access is enabled
        @JSONField(name = "use_https") boolean useHttps,

        // For API ≥ v2.0.1: Path to SSL keyfile
        @JSONField(name = "web_ui_https_key_path") String webUiHttpsKeyPath,

        // For API ≥ v2.0.1: Path to SSL certificate
        @JSONField(name = "web_ui_https_cert_path") String webUiHttpsCertPath,

        // True if server DNS should be updated dynamically
        @JSONField(name = "dyndns_enabled") boolean dyndnsEnabled,

        @JSONField(name = "dyndns_service") DynDnsService dyndnsService,

        // Username for DDNS service
        @JSONField(name = "dyndns_username") String dyndnsUsername,

        // Password for DDNS service
        @JSONField(name = "dyndns_password") String dyndnsPassword,

        // Your DDNS domain name
        @JSONField(name = "dyndns_domain") String dyndnsDomain,

        // RSS refresh interval
        @JSONField(name = "rss_refresh_interval") int rssRefreshInterval,

        // Max stored articles per RSS feed
        @JSONField(name = "rss_max_articles_per_feed") int rssMaxArticlesPerFeed,

        // Enable processing of RSS feeds
        @JSONField(name = "rss_processing_enabled") boolean rssProcessingEnabled,

        // Enable auto-downloading of torrents from the RSS feeds
        @JSONField(name = "rss_auto_downloading_enabled") boolean rssAutoDownloadingEnabled,

        // For API ≥ v2.5.1: Enable downloading of repack/proper Episodes
        @JSONField(name = "rss_download_repack_proper_episodes") boolean rssDownloadRepackProperEpisodes,

        // For API ≥ v2.5.1: List of RSS Smart Episode Filters
        @JSONField(name = "rss_smart_episode_filters") String rssSmartEpisodeFilters,

        // Enable automatic adding of trackers to new torrents
        @JSONField(name = "add_trackers_enabled") boolean addTrackersEnabled,

        // List of trackers to add to new torrent
        @JSONField(name = "add_trackers") String addTrackers,

        // For API ≥ v2.5.1: Enable custom http headers
        @JSONField(name = "web_ui_use_custom_http_headers_enabled") boolean webUiUseCustomHttpHeadersEnabled,

        // For API ≥ v2.5.1: List of custom http headers
        @JSONField(name = "web_ui_custom_http_headers") String webUiCustomHttpHeaders,

        // True enables max seeding time
        @JSONField(name = "max_seeding_time_enabled") boolean maxSeedingTimeEnabled,

        // Number of minutes to seed a torrent
        @JSONField(name = "max_seeding_time") int maxSeedingTime,

        //
        @JSONField(name = "announce_ip") String announceIp,

        // True always announce to all tiers
        @JSONField(name = "announce_to_all_tiers") boolean announceToAllTiers,

        // True always announce to all trackers in a tier
        @JSONField(name = "announce_to_all_trackers") boolean announceToAllTrackers,

        // Number of asynchronous I/O threads
        @JSONField(name = "async_io_threads") int asyncIoThreads,

        // List of banned IPs
        @JSONField(name = "banned_IPs") String bannedIps,

        // Outstanding memory when checking torrents in MiB
        @JSONField(name = "checking_memory_use") int checkingMemoryUse,

        // IP Address to bind to. Empty String means All addresses
        @JSONField(name = "current_interface_address") String currentInterfaceAddress,

        // Network Interface used
        @JSONField(name = "current_network_interface") String currentNetworkInterface,

        // Disk cache used in MiB
        @JSONField(name = "disk_cache") int diskCache,

        // Disk cache expiry interval in seconds
        @JSONField(name = "disk_cache_ttl") int diskCacheTtl,

        // Port used for embedded tracker
        @JSONField(name = "embedded_tracker_port") int embeddedTrackerPort,

        // True enables coalesce reads & writes
        @JSONField(name = "enable_coalesce_read_write") boolean enableCoalesceReadWrite,

        // True enables embedded tracker
        @JSONField(name = "enable_embedded_tracker") boolean enableEmbeddedTracker,

        // True allows multiple connections from the same IP address
        @JSONField(name = "enable_multi_connections_from_same_ip") boolean enableMultiConnectionsFromSameIp,

        // True enables os cache
        @JSONField(name = "enable_os_cache") boolean enableOsCache,

        // True enables sending of upload piece suggestions
        @JSONField(name = "enable_upload_suggestions") boolean enableUploadSuggestions,

        // File pool size
        @JSONField(name = "file_pool_size") int filePoolSize,

        // Maximal outgoing port (0: Disabled)
        @JSONField(name = "outgoing_ports_max") int outgoingPortsMax,

        // Minimal outgoing port (0: Disabled)
        @JSONField(name = "outgoing_ports_min") int outgoingPortsMin,

        // True rechecks torrents on completion
        @JSONField(name = "recheck_completed_torrents") boolean recheckCompletedTorrents,

        // True resolves peer countries
        @JSONField(name = "resolve_peer_countries") boolean resolvePeerCountries,

        // Save resume data interval in min
        @JSONField(name = "save_resume_data_interval") int saveResumeDataInterval,

        // Send buffer low watermark in KiB
        @JSONField(name = "send_buffer_low_watermark") int sendBufferLowWatermark,

        // Send buffer watermark in KiB
        @JSONField(name = "send_buffer_watermark") int sendBufferWatermark,

        // Send buffer watermark factor in per cent
        @JSONField(name = "send_buffer_watermark_factor") int sendBufferWatermarkFactor,

        // Socket backlog size
        @JSONField(name = "socket_backlog_size") int socketBacklogSize,

        // Upload choking algorithm used
        @JSONField(name = "upload_choking_algorithm") UploadChokingAlgorithm uploadChokingAlgorithm,

        // Upload slots behaviour used
        @JSONField(name = "upload_slots_behavior") UploadSlotsBehavior uploadSlotsBehavior,

        // UPnP lease duration (0: Permanent lease)
        @JSONField(name = "upnp_lease_duration") int upnpLeaseDuration,

        // μTP-TCP mixed mode algorithm
        @JSONField(name = "utp_tcp_mixed_mode") UtpTcpMixedMode utpTcpMixedMode
) {
}