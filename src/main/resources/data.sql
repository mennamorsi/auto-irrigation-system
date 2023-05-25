INSERT INTO plot (id,plot_code,plot_length,plot_width,plot_type) VALUES
(100,'A1',12,10,'Type A'),
(200,'A2',10,10,'Type A'),
(300,'B1',8,10,'Type B'),
(400,'B2',4,10,'Type B')
;

INSERT INTO configuration (id,plot_id,time_span_in_days,number_of_blocked_retries,retry_flag,number_of_retry) VALUES
(100, 100, 1, 1, true, 3),
(200, 200, 2, 0, true, 3),
(300, 300, 1, 2, true, 3),
(400, 400, 3, 0, true, 3)
;


