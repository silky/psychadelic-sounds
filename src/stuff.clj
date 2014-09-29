; -- Things

; Some sort of hat.
(definst c-hat [amp 0.8 t 0.02]
  (let [env (env-gen (perc 0.001 t) 1 1 0 1 FREE)
        noise (white-noise)
        sqr (* (env-gen (perc 0.01 0.02)) (pulse 1080 1))
        filt (bpf (+ sqr noise) 5000 0.5)]
    (* amp env filt)))

; Kick drum
(definst kick [freq 120 dur 0.5 width 0.5]
  (let [freq-env (* freq (env-gen (perc 0 (* 0.99 dur))))
        env (env-gen (perc 0.01 dur) 1 1 0 1 FREE)
        sqr (* (env-gen (perc 0 0.01)) (pulse (* 2 freq) width))
        src (sin-osc freq-env)
        drum (+ sqr (* env src))]
    (compander drum drum 0.2 1 0.1 0.01 0.01)))

