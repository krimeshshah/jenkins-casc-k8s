# --- Stage 1: Add bash/sleep tools ---
FROM alpine:3.18 AS tools
RUN apk add --no-cache bash coreutils

# --- Stage 2: Build final Kaniko image ---
FROM gcr.io/kaniko-project/executor:latest

# Copy the minimal tools we need from the first stage
COPY --from=tools /bin/bash /bin/bash
COPY --from=tools /bin/sleep /bin/sleep
COPY --from=tools /usr/bin/timeout /usr/bin/timeout

# Drop back to the non-root Kaniko user (UID 1000)
USER 1000

# Keep the container alive until Jenkins invokes /kaniko/executor manually
ENTRYPOINT ["/bin/bash", "-c", "sleep infinity"]
