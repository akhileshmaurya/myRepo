var gulp = require('gulp'),
    uglify = require('gulp-uglify'),
    rename = require('gulp-rename'),
    concat = require('gulp-concat'),
    browserSync = require('browser-sync'),
	livereload = require('gulp-livereload'),
	cssmin = require('gulp-minify-css'),
    del = require('del');

var reload = browserSync.reload;

gulp.task('scripts', function() {
  return gulp.src('src/js/*.js')
    .pipe(concat('app.js'))
    .pipe(gulp.dest('dist/js/'))
    .pipe(uglify())
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('dist/js/'));
});

gulp.task('styles', function() {
  return gulp.src('src/css/*.css')
    .pipe(concat('app.css'))
    .pipe(gulp.dest('dist/css/'))
    .pipe(rename({
      suffix: '.min'
    }))
    .pipe(gulp.dest('dist/css/'));
});
gulp.task('main', function() {
  return gulp.src('src/**/*.html')
    .pipe(gulp.dest('dist/'));
});
gulp.task('clean', function(cb) {
    del(['dist'], cb)
});

gulp.task('default', ['clean'], function(){
  gulp.start( 'main','styles', 'scripts' );
});

gulp.task('serve', function() {
  browserSync({
    server: {
      baseDir: 'dist'
    }
  });
  gulp.watch('src/css/**/*.css', ['styles']);
  gulp.watch('src/js/**/*.js', ['scripts']);
  gulp.watch('src/**/*.html', ['main']);
  livereload.listen();
  gulp.watch(['dist/**']).on('change', livereload.changed);
});