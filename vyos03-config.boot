interfaces {
    ethernet eth0 {
        address 10.0.17.105/24
        duplex auto
        hw-id 00:50:56:a1:08:bc
        smp_affinity auto
        speed auto
        vrrp {
            vrrp-group 155 {
                advertise-interval 1
                preempt true
                priority 150
                sync-group wansync-group5
                virtual-address 10.0.17.75
            }
        }
    }
    ethernet eth1 {
        address 10.0.5.2/24
        duplex auto
        hw-id 00:50:56:a1:49:9c
        smp_affinity auto
        speed auto
        vrrp {
            vrrp-group 155 {
                advertise-interval 1
                preempt true
                priority 150
                sync-group lansync-group5
                virtual-address 10.0.5.1
            }
        }
    }
    loopback lo {
    }
}
nat {
    destination {
        rule 10 {
            destination {
                port 80
            }
            inbound-interface eth0
            protocol tcp
            translation {
                address 10.0.5.100
            }
        }
        rule 20 {
            destination {
                port 22
            }
            inbound-interface eth0
            protocol tcp
            translation {
                address 10.0.5.100
            }
        }
    }
    source {
        rule 10 {
            outbound-interface eth0
            source {
                address 10.0.5.0/24
            }
            translation {
                address masquerade
            }
        }
    }
}
service {
    dns {
        forwarding {
            cache-size 150
            listen-on eth1
        }
    }
    ssh {
        listen-address 10.0.17.75
        listen-address 10.0.5.2
        port 22
    }
}
system {
    config-management {
        commit-revisions 20
    }
    console {
    }
    gateway-address 10.0.17.2
    host-name vyos03-james
    login {
        user deployer {
            authentication {
                encrypted-password $6$vH2t.BYSny.cpu$5Izlqp9WA08Ulp2.zvlwEH3lQfpuwP5E1uRK7GdJK0hN.qdmgc76QUqJsRFIKu/FNYeaM3/Khx0UVxznrSaEB.
                plaintext-password ""
            }
            level admin
        }
        user james {
            authentication {
                encrypted-password $6$kbEshQhWp7CWw$U4P39OEknF5cVR2dleZzB59OddSooF2OkyPxF265GLHgE5B5Kj1iVg/4GqX6xhbXEj5JEi.ylMTeZ.iOzJSRO1
                plaintext-password ""
            }
            level admin
        }
        user vyos {
            authentication {
                encrypted-password $1$Z3Djkc0B$q3Af6YmovVwMO8S5frV2F.
                plaintext-password ""
            }
            level admin
        }
    }
    name-server 10.0.17.2
    ntp {
        server 0.pool.ntp.org {
        }
        server 1.pool.ntp.org {
        }
        server 2.pool.ntp.org {
        }
    }
    package {
        auto-sync 1
        repository community {
            components main
            distribution helium
            password ""
            url http://packages.vyos.net/vyos
            username ""
        }
    }
    syslog {
        global {
            facility all {
                level notice
            }
            facility protocols {
                level debug
            }
        }
    }
    time-zone UTC
}


/* Warning: Do not remove the following line. */
/* === vyatta-config-version: "system@6:conntrack-sync@1:webgui@1:webproxy@1:quagga@2:qos@1:dhcp-server@4:firewall@5:zone-policy@1:ipsec@4:wanloadbalance@3:conntrack@1:nat@4:config-management@1:dhcp-relay@1:vrrp@1:cron@1:cluster@1" === */
/* Release version: VyOS 1.1.8 */
