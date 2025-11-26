LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://main.c \
           file://my-c-app.service"

S = "${WORKDIR}"

# 1. REQUIRED: Inherit the systemd class
inherit systemd

# 2. REQUIRED: Tell systemd class which service file belongs to this recipe
SYSTEMD_SERVICE:${PN} = "my-c-app.service"

do_compile() {
    ${CC} ${CFLAGS} ${LDFLAGS} main.c -o my-c-app
}

do_install() {
    # Install the executable
    install -d ${D}${bindir}
    install -m 0755 my-c-app ${D}${bindir}

    # Install the service file
    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${WORKDIR}/my-c-app.service ${D}${systemd_system_unitdir}
}

# 3. CRITICAL: Add the service file to the package files list
# If you miss this, the service file is deleted during packaging!
FILES:${PN} += "${systemd_system_unitdir}/my-c-app.service"
