FROM redis

COPY sentinel.conf /etc/redis/sentinel.conf
RUN chown redis:redis /etc/redis/sentinel.conf

CMD ["redis-sentinel", "/etc/redis/sentinel.conf"]