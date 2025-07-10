
```sh
# https://www.chisel-lang.org/docs/installation
sudo apt install -y openjdk-21-jdk gtkwave help2man make autoconf g++ flex bison ccache libgoogle-perftools-dev numactl perl-doc

# 安装SBT
# https://www.scala-sbt.org/download/
echo "deb https://repo.scala-sbt.org/scalasbt/debian all main" | sudo tee /etc/apt/sources.list.d/sbt.list
echo "deb https://repo.scala-sbt.org/scalasbt/debian /" | sudo tee /etc/apt/sources.list.d/sbt_old.list
curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo tee /etc/apt/trusted.gpg.d/sbt.asc
sudo apt update
sudo apt install -y sbt

# 安装Verilator
# https://verilator.org/guide/latest/install.html#git-install
git clone https://github.com/verilator/verilator && cd verilator
autoconf && ./configure
make -j `nproc`
sudo make install

